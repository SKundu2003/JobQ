package com.jobq.Job_server.common.al.readal;

import com.jobq.Job_server.common.models.JobDomainDetails;

import java.util.List;
import java.util.Optional;

public interface JobDomainReadAl {

    Optional<JobDomainDetails> findJobDomainById(Long id);

    Optional<List<JobDomainDetails>> fetchAllJobDomains(String jobDomainName);
}
