package com.hutech.travelmanagement.web.controller.admin.tour;

import com.hutech.travelmanagement.service.implement.TourServiceImpl;
import com.hutech.travelmanagement.web.dto.TourDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/tour/new")
public class NewTourController {
    private final TourServiceImpl tourService;

    @ModelAttribute("tour")
    public TourDto tourDto() {
        return new TourDto();
    }

    public NewTourController(TourServiceImpl tourService) {
        this.tourService = tourService;
    }

    @GetMapping
    public String showNewTourPage() {
        return "admin/layouts/tour/new_tour";
    }

    @PostMapping
    public String newTour(@RequestParam("file") MultipartFile file, @ModelAttribute("tour") TourDto tourDto) {
        if(tourService.createTour(tourDto, file)) {
            return "redirect:/admin/tour?new_tour_success";
        }
        return "redirect:/admin/tour?new_tour_error";
    }
}
