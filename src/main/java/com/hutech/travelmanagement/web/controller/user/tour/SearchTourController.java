package com.hutech.travelmanagement.web.controller.user.tour;

import com.hutech.travelmanagement.helper.ModelAndViewHelper;
import com.hutech.travelmanagement.service.implement.TourServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tour/search")
public class SearchTourController {
    private final TourServiceImpl tourService;
    private final ModelAndViewHelper modelAndViewHelper;

    public SearchTourController(TourServiceImpl tourService, ModelAndViewHelper modelAndViewHelper) {
        this.tourService = tourService;
        this.modelAndViewHelper = modelAndViewHelper;
    }

    @GetMapping("/price/{type}")
    public ModelAndView searchByPrice(@PathVariable int type){
        ModelAndView modelAndView = modelAndViewHelper.getMAV("public/index");
        modelAndView.addObject("tours", tourService.searchByPrice(type));
        return modelAndView;
    }

    @GetMapping("/date/{day}")
    public ModelAndView searchByDate(@PathVariable("day") int day){
        ModelAndView modelAndView = modelAndViewHelper.getMAV("public/index");
        modelAndView.addObject("tours", tourService.searchByDate(day));
        return modelAndView;
    }
}
