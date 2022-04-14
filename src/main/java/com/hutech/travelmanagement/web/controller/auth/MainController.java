package com.hutech.travelmanagement.web.controller.auth;

import com.hutech.travelmanagement.web.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class MainController {
    @ModelAttribute("user")
    public UserDto userDto() {
        return new UserDto();
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
}
