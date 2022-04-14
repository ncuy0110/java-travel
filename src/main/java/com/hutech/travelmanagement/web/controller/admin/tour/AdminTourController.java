package com.hutech.travelmanagement.web.controller.admin.tour;

import com.hutech.travelmanagement.service.implement.TourServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminTourController {
    private final TourServiceImpl tourServiceImpl;

    public AdminTourController(TourServiceImpl tourServiceImpl) {
        this.tourServiceImpl = tourServiceImpl;
    }

    @GetMapping("/admin/tour")
    public ModelAndView showAllTour() {
        ModelAndView mav = new ModelAndView("admin/layouts/tour/index");
        mav.addObject("tours", tourServiceImpl.getAllTours());
        return mav;
    }

    @GetMapping("/admin/tour/delete/{id}")
    public String deleteTour(@PathVariable("id") Long id){
        System.out.println(id);
        if(tourServiceImpl.deleteTour(id)){
            return "redirect:/admin/tour?delete_success";
        }
        return "redirect:/admin/tour?delete_failed";
    }
}
