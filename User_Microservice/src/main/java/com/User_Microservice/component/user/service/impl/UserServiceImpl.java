package com.User_Microservice.component.user.service.impl;

import com.User_Microservice.component.user.al.readAl.UserReadAl;
import com.User_Microservice.component.user.al.writeAl.UserWriteAl;
import com.User_Microservice.component.user.entity.UserEntity;
import com.User_Microservice.component.user.model.UserDetailsModule;
import com.User_Microservice.component.user.request.CreateUserRequest;
import com.User_Microservice.component.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserReadAl userReadAl;

    private final UserWriteAl userWriteAl;

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    @Autowired
    public UserServiceImpl(UserReadAl userReadAl, UserWriteAl userWriteAl) {
        this.userReadAl = userReadAl;
        this.userWriteAl = userWriteAl;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userByUserName = userReadAl.getUserByUserName(username);
        if (!userByUserName.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return MODEL_MAPPER.map(userByUserName.get(), UserDetails.class);

    }

    @Override
    public Optional<UserDetailsModule> createUser(CreateUserRequest createUserRequest) {
        return userWriteAl.createUser(createUserRequest);
    }
}
