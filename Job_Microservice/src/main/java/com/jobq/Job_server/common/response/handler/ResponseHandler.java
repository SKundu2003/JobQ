package com.jobq.Job_server.common.response.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(String status, String message,Object responseObject, HttpStatus httpStatus) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status);
        map.put("message", message);
        map.put("data", responseObject);

        return new ResponseEntity<>(map, httpStatus);
    }
}
