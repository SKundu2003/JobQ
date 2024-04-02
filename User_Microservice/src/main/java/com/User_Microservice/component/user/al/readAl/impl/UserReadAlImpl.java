package com.User_Microservice.component.user.al.readAl.impl;

import com.User_Microservice.component.user.al.readAl.UserReadAl;
import com.User_Microservice.component.user.entity.UserEntity;
import com.User_Microservice.component.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserReadAlImpl implements UserReadAl {

    private final UserRepository userRepository;

    @Autowired
    public UserReadAlImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserEntity> getUserByUserName(String username) {
        return userRepository.findByUserName(username);
    }
}
