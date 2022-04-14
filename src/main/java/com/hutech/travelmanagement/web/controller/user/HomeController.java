package com.hutech.travelmanagement.web.controller.user;

import com.hutech.travelmanagement.helper.ModelAndViewHelper;
import com.hutech.travelmanagement.repository.TourRepository;
import com.hutech.travelmanagement.service.implement.TourServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
    private final TourServiceImpl tourService;
    private final ModelAndViewHelper modelAndViewHelper;

    public HomeController(TourServiceImpl tourService, ModelAndViewHelper modelAndViewHelper) {
        this.tourService = tourService;
        this.modelAndViewHelper = modelAndViewHelper;
    }

    @GetMapping
    public ModelAndView showHomePage(){
        ModelAndView modelAndView = modelAndViewHelper.getMAV("public/index");
        modelAndView.addObject("tours", tourService.getAllTours());
        return modelAndView;
    }
}
