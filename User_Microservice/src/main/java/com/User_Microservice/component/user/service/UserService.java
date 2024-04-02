package com.User_Microservice.component.user.service;

import com.User_Microservice.component.user.model.UserDetailsModule;
import com.User_Microservice.component.user.request.CreateUserRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserService {
    Optional<UserDetailsModule> createUser(CreateUserRequest createUserRequest);

}
