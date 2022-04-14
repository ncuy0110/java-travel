package com.hutech.travelmanagement.web.controller.admin.tour;

import com.hutech.travelmanagement.model.Tour;
import com.hutech.travelmanagement.service.implement.TourServiceImpl;
import com.hutech.travelmanagement.web.dto.TourDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/admin/tour/edit")
public class EditTourController {
    private final TourServiceImpl tourService;

    @ModelAttribute("tour")
    public TourDto tourDto() {
        return new TourDto();
    }

    public EditTourController(TourServiceImpl tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/{id}")
    public ModelAndView showEditTourPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/layouts/tour/edit_tour");
        modelAndView.addObject("tour", tourService.findById(id));
        return modelAndView;
    }

    @PostMapping("/{id}")
    public String editTour(@PathVariable("id") Long id, @ModelAttribute("tour") TourDto tourDto, MultipartFile file) {
        if (tourService.editTour(id, tourDto, file)) return "redirect:/admin/tour?edit_success";
        return "redirect:/admin/tour?edit_failed";
    }
}
