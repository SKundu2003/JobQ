package com.User_Microservice.component.user.al.writeAl.impl;

import com.User_Microservice.component.user.al.writeAl.UserWriteAl;
import com.User_Microservice.component.user.entity.UserEntity;
import com.User_Microservice.component.user.model.UserDetailsModule;
import com.User_Microservice.component.user.repository.UserRepository;
import com.User_Microservice.component.user.request.CreateUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserWriteAlImpl implements UserWriteAl {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    @Autowired
    public UserWriteAlImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserDetailsModule> createUser(CreateUserRequest createUserRequest){
        UserEntity userDetailsModule = MODEL_MAPPER.map(createUserRequest, UserEntity.class);
        userDetailsModule.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        UserEntity save = userRepository.save(userDetailsModule);
        return Optional.of(MODEL_MAPPER.map(save, UserDetailsModule.class));
    }

}
