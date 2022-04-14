package com.hutech.travelmanagement.service.implement;

import com.hutech.travelmanagement.helper.UsernameHelper;
import com.hutech.travelmanagement.model.BookedTour;
import com.hutech.travelmanagement.model.Tour;
import com.hutech.travelmanagement.model.User;
import com.hutech.travelmanagement.repository.BookedTourRepository;
import com.hutech.travelmanagement.repository.TourRepository;
import com.hutech.travelmanagement.repository.UserRepository;
import com.hutech.travelmanagement.service.interfaces.BookedTourService;
import com.hutech.travelmanagement.web.dto.BookingDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BookedTourServiceImpl implements BookedTourService {
    private final TourRepository tourRepository;
    private final BookedTourRepository bookedTourRepository;
    private final TourServiceImpl tourService;
    private final UsernameHelper usernameHelper;
    private final UserRepository userRepository;

    public BookedTourServiceImpl(TourRepository tourRepository, BookedTourRepository bookedTourRepository, TourServiceImpl tourService, UsernameHelper usernameHelper, UserRepository userRepository) {
        this.tourRepository = tourRepository;
        this.bookedTourRepository = bookedTourRepository;
        this.tourService = tourService;
        this.usernameHelper = usernameHelper;
        this.userRepository = userRepository;
    }

    @Override
    public boolean bookTour(BookingDto bookingDto, Long tourId) {
        try {
            Tour tour = tourService.findById(tourId);
            User user = userRepository.findByUsername(usernameHelper.getCurrentUsername());
            if (tour == null || user == null || (bookingDto.getAdultQuantity() + bookingDto.getChildrenQuantity() < 1) ||
                    (tour.getSold() + bookingDto.getAdultQuantity() + bookingDto.getChildrenQuantity()) > tour.getTotal())
                return false;
            BookedTour bookedTour = new BookedTour(bookingDto.getAdultQuantity(), bookingDto.getChildrenQuantity(), user, tour);
            tour.setSold(tour.getSold() + bookedTour.getAdultQuantity() + bookedTour.getChildrenQuantity());
            bookedTourRepository.save(bookedTour);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<BookedTour> findAll() {
        return bookedTourRepository.findAll();
    }

    @Override
    public List<BookedTour> getTourOfMonth() {
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, -31);
        List<BookedTour> bookedTours = new ArrayList<>();
        bookedTourRepository.findAllByCreatedAtGreaterThanEqual(LocalDateTime.ofInstant(c.toInstant(), ZoneId.systemDefault()))
                .forEach(bookedTour -> {
                    if (bookedTours.size() == 0) bookedTours.add(bookedTour);
                    else {
                        int i = bookedTours.size() -1;
                        if (bookedTour.compareTo(bookedTours.get(i))) {
                            bookedTour.setTotalCost(bookedTour.getTotalCost() + bookedTours.get(i).getTotalCost());
                            bookedTours.set(i, bookedTour);
                        } else bookedTours.add(bookedTour);
                    }
                });
        return bookedTours;
    }
}
