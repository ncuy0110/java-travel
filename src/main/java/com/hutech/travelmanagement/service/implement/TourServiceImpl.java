package com.hutech.travelmanagement.service.implement;

import com.hutech.travelmanagement.model.Tour;
import com.hutech.travelmanagement.repository.TourRepository;
import com.hutech.travelmanagement.service.interfaces.StorageService;
import com.hutech.travelmanagement.service.interfaces.TourService;
import com.hutech.travelmanagement.web.dto.TourDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;
    private final StorageService storageService;

    public TourServiceImpl(TourRepository tourRepository, StorageService storageService) {
        this.tourRepository = tourRepository;
        this.storageService = storageService;
    }

    @Override
    public List<Tour> getAllTours() {
        List<Tour> tours = tourRepository.findAll();
        int i = 0;
        for (Tour tour : tours) {
            if (tour.getTitle().length() > 134) {
                tour.setTitle(tour.getTitle().substring(0, 134) + "...");
                tours.set(i, tour);
            }
            i++;
        }
        return tours;
    }

    @Override
    public boolean createTour(TourDto tourDto, MultipartFile file) {
        try {
            String name = storageService.store(file);
            Tour tour = new Tour(tourDto, name);
            tourRepository.save(tour);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteTour(Long id) {
        try {
            tourRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editTour(Long id, TourDto tourDto, MultipartFile image) {
        try {
            String name = storageService.store(image);
            Tour newTour = new Tour(tourDto, name);
            Tour editTour = tourRepository.findById(id).orElse(null);
            if(editTour==null) return false;
            newTour.setId(id);
            tourRepository.save(newTour);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Tour findById(Long id) {
        return tourRepository.findById(id).orElse(null);
    }

    @Override
    public List<Tour> searchByName(String name) {
        List<Tour> tours = new ArrayList<>();
        tourRepository.findAll().forEach(tour -> {
            if (tour.compareByName(name)) {
                tours.add(tour);
            }
        });
        return tours;
    }

    @Override
    public List<Tour> searchByPrice(int type) {
        List<Tour> tours = new ArrayList<>();
        getAllTours().forEach(tour -> {
            switch (type) {
                case 1:
                    if (tour.getAdultPrice() < 1000000 || tour.getChildrenPrice() < 1000000) tours.add(tour);
                    break;
                case 2:
                    if ((tour.getAdultPrice() > 1000000 && tour.getAdultPrice() < 2000000) || (tour.getChildrenPrice() > 1000000 && tour.getChildrenPrice() < 2000000))
                        tours.add(tour);
                    break;
                case 3:
                    if (tour.getAdultPrice() > 2000000 || tour.getChildrenPrice() > 2000000) tours.add(tour);
                    break;
            }
        });
        return tours;
    }

    @Override
    public List<Tour> searchByDate(int day) {
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, day);
        return tourRepository.findAllByDepartureDayLessThanEqualAndDepartureDayGreaterThanEqual(c.getTime(), currentDate);
    }
}
