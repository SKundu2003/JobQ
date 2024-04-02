package com.User_Microservice.component.user.controller;

import com.User_Microservice.common.handler.ResponseHandler;
import com.User_Microservice.component.user.request.CreateUserRequest;
import com.User_Microservice.component.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test() {
        return "User Microservice is working!";
    }


    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest user) {
        return ResponseHandler.generateResponse("success", "User created successfully",
                userService.createUser(user), HttpStatus.CREATED, 201);
    }

}
