package com.User_Microservice.component.user.al.readAl;

import com.User_Microservice.component.user.entity.UserEntity;

import java.util.Optional;

public interface UserReadAl {

    public Optional<UserEntity> getUserByUserName(String username);
}
