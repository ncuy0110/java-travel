package com.hutech.travelmanagement.helper;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ModelAndViewHelper {
    private final UsernameHelper usernameHelper;

    public ModelAndViewHelper(UsernameHelper usernameHelper) {
        this.usernameHelper = usernameHelper;
    }

    @Bean
    public ModelAndView getMAV(String viewName){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(viewName);
        modelAndView.addObject("username", usernameHelper.getCurrentUsername());
        return modelAndView;
    }
}
