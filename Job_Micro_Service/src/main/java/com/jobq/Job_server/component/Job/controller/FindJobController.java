package com.jobq.Job_server.component.Job.controller;

import com.jobq.Job_server.common.response.handler.ResponseHandler;
import com.jobq.Job_server.component.Job.module.JobDetails;
import com.jobq.Job_server.component.Job.request.CreateJobRequest;
import com.jobq.Job_server.component.Job.request.FindJobRequest;
import com.jobq.Job_server.component.Job.response.CompleteJobDetailsResponse;
import com.jobq.Job_server.component.Job.response.JobDetailsResponse;
import com.jobq.Job_server.component.Job.service.JobService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/job",produces = MediaType.APPLICATION_JSON_VALUE) // it is used to define the controller path and the response type
public class FindJobController {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    private final JobService jobService;

    @Autowired
    public FindJobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseHandler.generateResponse("success", "Test", "Dummy Data", HttpStatus.OK);
    }

    @PostMapping("/find")
    public ResponseEntity<?> findJob(@RequestBody FindJobRequest jobRequest) {
        Optional<JobDetailsResponse> jobDetailsResponse = jobService.fetchRelevantJobs(jobRequest);
        if(!jobDetailsResponse.isPresent()){
            return ResponseHandler.generateResponse("failure", "No relevant jobs found", null, HttpStatus.BAD_REQUEST);
        }
        return ResponseHandler.generateResponse("success", "All relevant jobs found", jobDetailsResponse.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> fetchJobById(@PathVariable Long id) {
        Optional<CompleteJobDetailsResponse> completeJobDetailsResponse = jobService.fetchJobById(id);
        if(!completeJobDetailsResponse.isPresent()){
            return ResponseHandler.generateResponse("failure", "No job found with the given id", null, HttpStatus.BAD_REQUEST);
        }
        return ResponseHandler.generateResponse("success", "Job found", completeJobDetailsResponse.get(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createJob(@RequestBody CreateJobRequest createJobRequest) {
        Optional<JobDetails> jobDetailsResponse = jobService.createJob(createJobRequest);
        if(!jobDetailsResponse.isPresent()){
            return ResponseHandler.generateResponse("failure", "Job creation failed", null, HttpStatus.BAD_REQUEST);
        }
        return ResponseHandler.generateResponse("success", "Job created", jobDetailsResponse.get(), HttpStatus.OK);
    }

}
