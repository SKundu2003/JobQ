package com.User_Microservice.common.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(
            String status, String message, Object responseObj, HttpStatus httpStatus, long statusCode
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status);
        map.put("message", message);
        map.put("data", responseObj);
        map.put("code", statusCode);

        return new ResponseEntity<>(map, httpStatus);
    }

}
