package com.User_Microservice.component.user.al.writeAl;

import com.User_Microservice.component.user.model.UserDetailsModule;
import com.User_Microservice.component.user.request.CreateUserRequest;

import java.util.Optional;

public interface UserWriteAl {

    Optional<UserDetailsModule> createUser(CreateUserRequest createUserRequest);
}
