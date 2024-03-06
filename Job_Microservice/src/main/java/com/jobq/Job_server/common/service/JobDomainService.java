package com.jobq.Job_server.common.service;

import com.jobq.Job_server.common.models.JobDomainDetails;

import java.util.List;
import java.util.Optional;

public interface JobDomainService {

    Optional<JobDomainDetails> findJobDomainById(Long id);

    Optional<List<JobDomainDetails>> fetchAllJobDomains(String jobDomainName);
}
