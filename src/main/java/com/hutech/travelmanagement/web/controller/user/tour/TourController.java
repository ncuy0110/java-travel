package com.hutech.travelmanagement.web.controller.user.tour;

import com.hutech.travelmanagement.helper.ModelAndViewHelper;
import com.hutech.travelmanagement.service.implement.TourServiceImpl;
import com.hutech.travelmanagement.web.dto.BookingDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TourController {
    private final TourServiceImpl tourService;
    private final ModelAndViewHelper modelAndViewHelper;

    @ModelAttribute("booking")
    public BookingDto bookingDto() {
        return new BookingDto();
    }

    public TourController(TourServiceImpl tourService, ModelAndViewHelper modelAndViewHelper) {
        this.tourService = tourService;
        this.modelAndViewHelper = modelAndViewHelper;
    }

    @GetMapping("/tour/search-by-name")
    public ModelAndView searchByName(@RequestParam(value = "name") String name) {
        ModelAndView modelAndView = modelAndViewHelper.getMAV("public/index");
        modelAndView.addObject("tours", tourService.searchByName(name));
        return modelAndView;
    }

    @GetMapping("/tour/details/{id}")
    public ModelAndView showDetailsTour(@PathVariable("id") Long id) {
        ModelAndView modelAndView = modelAndViewHelper.getMAV("public/tour/tour_details");
        modelAndView.addObject("tour", tourService.findById(id));
        return modelAndView;
    }
}
