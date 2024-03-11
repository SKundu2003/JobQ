package com.jobq.Job_server.component.Job.al.readal;

import com.jobq.Job_server.component.Job.module.JobAndDomainMappingDetails;

import java.util.List;
import java.util.Optional;

public interface JobAndDomainMappingReadAl {

    Optional<List<JobAndDomainMappingDetails>> fetchJobAndDomainMappingDetails(Long jobId);
}
