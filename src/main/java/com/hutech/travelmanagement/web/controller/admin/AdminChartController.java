package com.hutech.travelmanagement.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/chart")
public class AdminChartController {
    @GetMapping("/month")
    public String showChartMonth(){
        return "/admin/layouts/chart/month";
    }
}
