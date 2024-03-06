package com.jobq.Job_server.component.Job.al.writeal;

import com.jobq.Job_server.component.Job.module.JobDetails;
import com.jobq.Job_server.component.Job.request.CreateJobRequest;

import java.util.Optional;

public interface JobWriteAl {

    Optional<JobDetails> createJob(CreateJobRequest createJobRequest);
}
