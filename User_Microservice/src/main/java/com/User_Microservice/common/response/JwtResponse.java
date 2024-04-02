package com.User_Microservice.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {

    @JsonProperty("access_token")
    private String jwtToken;

    private String type = "Bearer";

    @JsonProperty("user_name")
    private String userName;
}
