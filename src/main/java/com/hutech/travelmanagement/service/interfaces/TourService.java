package com.hutech.travelmanagement.service.interfaces;

import com.hutech.travelmanagement.model.Tour;
import com.hutech.travelmanagement.web.dto.TourDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TourService {
//    Admin
    boolean createTour(TourDto tourDto, MultipartFile file);
    boolean deleteTour(Long id);
    boolean editTour(Long id, TourDto tourDto, MultipartFile image);
    List<Tour> getAllTours();
    Tour findById(Long id);
    List<Tour> searchByName(String name);
    List<Tour> searchByPrice(int type);
    List<Tour> searchByDate(int day);
}
