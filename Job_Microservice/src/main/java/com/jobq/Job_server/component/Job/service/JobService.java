package com.jobq.Job_server.component.Job.service;

import com.jobq.Job_server.component.Job.module.JobDetails;
import com.jobq.Job_server.component.Job.request.CreateJobRequest;
import com.jobq.Job_server.component.Job.request.FindJobRequest;
import com.jobq.Job_server.component.Job.response.CompleteJobDetailsResponse;
import com.jobq.Job_server.component.Job.response.CreateJobResponse;
import com.jobq.Job_server.component.Job.response.JobDetailsResponse;

import java.util.Optional;

public interface JobService {

    Optional<JobDetailsResponse> fetchRelevantJobs(FindJobRequest jobRequest);

    Optional<CompleteJobDetailsResponse> fetchJobById(Long id);

    Optional<CreateJobResponse> createJob(CreateJobRequest createJobRequest);

    /*
     TO DO : fetch user id from jwt token (context)
    * */
    Optional<Boolean> applyJob(Long jobId);
}
