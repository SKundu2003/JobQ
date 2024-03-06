package com.jobq.Job_server.common.controllers;

import com.jobq.Job_server.common.response.handler.ResponseHandler;
import com.jobq.Job_server.common.service.JobDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job-domain")
public class JobDomainController {

    private final JobDomainService jobDomainService;


    @Autowired
    public JobDomainController(JobDomainService jobDomainService) {
        this.jobDomainService = jobDomainService;
    }


    @GetMapping("/fetch")
    public ResponseEntity<?> fetchJobDomain(@RequestParam String domain) {
        return ResponseHandler.generateResponse("success", "Domain fetched successfully",
                jobDomainService.fetchAllJobDomains(domain), org.springframework.http.HttpStatus.OK);
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<?> fetchJobDomainById(@PathVariable("id") Long id) {
        return ResponseHandler.generateResponse("success", "Domain fetched successfully",
                jobDomainService.findJobDomainById(id), org.springframework.http.HttpStatus.OK);
    }

}
