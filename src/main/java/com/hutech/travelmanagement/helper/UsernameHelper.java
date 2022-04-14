package com.hutech.travelmanagement.helper;

import com.hutech.travelmanagement.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UsernameHelper {

    @Bean
    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth==null) return "";
        return auth.getName();
    }
}
