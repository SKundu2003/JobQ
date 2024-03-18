package com.User_Microservice.component.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDetails {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("created_by")
    private Long createdBy;

    @JsonProperty("created_on")
    private String createdOn;

    @JsonProperty("updated_by")
    private Long updatedBy;

    @JsonProperty("updated_on")
    private String updatedOn;
}
