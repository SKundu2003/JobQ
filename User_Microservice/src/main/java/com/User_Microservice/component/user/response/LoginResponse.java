package com.User_Microservice.component.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String message;
    private Long id;
    private String userName;
    private String email;
    private String phone;
    private String createdOn;
    private String updatedOn;
}
