package com.hutech.travelmanagement.web.controller.admin;

import com.hutech.travelmanagement.model.BookedTour;
import com.hutech.travelmanagement.service.implement.BookedTourServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@RestController
public class AdminBookingTourController {
    private final BookedTourServiceImpl bookedTourService;

    public AdminBookingTourController(BookedTourServiceImpl bookedTourService) {
        this.bookedTourService = bookedTourService;
    }

    @GetMapping("/admin/booked-tour/month")
    public ResponseEntity getListBookedOfMonth(){
        List<BookedTour> bookedTours = bookedTourService.getTourOfMonth();
        String[] label = new String[bookedTours.size()];
        Long[] data = new Long[bookedTours.size()];
        int i = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
        for(BookedTour bookedTour: bookedTours){
            label[i] = bookedTour.getCreatedAt().format(formatter);
            data[i] = bookedTour.getTotalCost();
            i++;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("label", label);
        hashMap.put("data", data);
        return ResponseEntity.ok(hashMap);
    }
}
