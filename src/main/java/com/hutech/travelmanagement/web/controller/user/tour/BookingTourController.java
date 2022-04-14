package com.hutech.travelmanagement.web.controller.user.tour;

import com.hutech.travelmanagement.helper.ModelAndViewHelper;
import com.hutech.travelmanagement.service.implement.TourServiceImpl;
import com.hutech.travelmanagement.service.interfaces.BookedTourService;
import com.hutech.travelmanagement.web.dto.BookingDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookingTourController {
    private final BookedTourService bookedTourService;
    private final ModelAndViewHelper modelAndViewHelper;
    private final TourServiceImpl tourService;

    public BookingTourController(BookedTourService bookedTourService, ModelAndViewHelper modelAndViewHelper, TourServiceImpl tourService) {
        this.bookedTourService = bookedTourService;
        this.modelAndViewHelper = modelAndViewHelper;
        this.tourService = tourService;
    }

    @ModelAttribute("booking")
    public BookingDto bookingDto() {
        return new BookingDto();
    }

    @PostMapping("/book-tour/{id}")
    public String bookTour(@ModelAttribute("booking") BookingDto bookingDto, @PathVariable("id") Long id) {
        return "redirect:/book-confirm?id=" + id + "&adult=" + bookingDto.getAdultQuantity() + "&children=" + bookingDto.getChildrenQuantity();
    }

    @GetMapping("/book-confirm")
    public ModelAndView confirmBooking(@RequestParam("id") Long id, @RequestParam("adult") int adult, @RequestParam("children") int children) {
        ModelAndView modelAndView = modelAndViewHelper.getMAV("/public/tour/book_confirm");
        modelAndView.addObject("tour", tourService.findById(id));
        modelAndView.addObject("adult_quantity", adult);
        modelAndView.addObject("children_quantity", children);
        return modelAndView;
    }

    @GetMapping("/book-confirm-end")
    public String confirmBookingEnd(@RequestParam("id") Long id, @RequestParam("adult") int adult, @RequestParam("children") int children) {
        if (bookedTourService.bookTour(new BookingDto(adult, children), id))
            return "redirect:/booked";
        return "redirect:/book-confirm?id=" + id + "&adult=" + adult + "&children=" + children + "&book_failed";
    }

    @GetMapping("booked")
    public ModelAndView showBookedPage(){
        ModelAndView modelAndView = modelAndViewHelper.getMAV("public/tour/booked");
        modelAndView.addObject("booked_list", bookedTourService.findAll());
        return modelAndView;
    }
}
