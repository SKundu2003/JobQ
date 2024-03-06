package com.jobq.Job_server.common.al.writeal;

import com.jobq.Job_server.common.models.JobDomainDetails;
import com.jobq.Job_server.common.request.CreateDomainRequest;

import java.util.Optional;

public interface JobDomainWriteAl {

    Optional<JobDomainDetails> createJobDomain(CreateDomainRequest createDomainRequest);
}
