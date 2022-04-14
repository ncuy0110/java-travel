package com.hutech.travelmanagement.service.interfaces;

import com.hutech.travelmanagement.model.User;
import com.hutech.travelmanagement.web.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    User save(UserDto userDto);



    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
