package com.hutech.travelmanagement.service.interfaces;

import com.hutech.travelmanagement.model.BookedTour;
import com.hutech.travelmanagement.web.dto.BookingDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookedTourService {
    boolean bookTour(BookingDto bookingDto, Long tourId);
    List<BookedTour> findAll();
    List<BookedTour> getTourOfMonth();
}
