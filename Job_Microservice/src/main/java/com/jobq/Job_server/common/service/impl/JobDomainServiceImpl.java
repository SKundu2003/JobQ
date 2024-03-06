package com.jobq.Job_server.common.service.impl;

import com.jobq.Job_server.common.al.readal.JobDomainReadAl;
import com.jobq.Job_server.common.al.writeal.JobDomainWriteAl;
import com.jobq.Job_server.common.models.JobDomainDetails;
import com.jobq.Job_server.common.request.CreateDomainRequest;
import com.jobq.Job_server.common.service.JobDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JobDomainServiceImpl implements JobDomainService {

    private final JobDomainReadAl jobDomainReadAl;

    private final JobDomainWriteAl jobDomainWriteAl;

    @Autowired
    public JobDomainServiceImpl(JobDomainReadAl jobDomainReadAl,JobDomainWriteAl jobDomainWriteAl) {
        this.jobDomainReadAl = jobDomainReadAl;
        this.jobDomainWriteAl = jobDomainWriteAl;
    }

    @Override
    public Optional<List<JobDomainDetails>> fetchAllJobDomains(String jobDomainName){
        return jobDomainReadAl.fetchAllJobDomains(jobDomainName);
    }

    @Override
    public Optional<JobDomainDetails> findJobDomainById(Long id){
        return jobDomainReadAl.findJobDomainById(id);
    }


    @Override
    public Optional<JobDomainDetails> createJobDomain(CreateDomainRequest createDomainRequest){
        return jobDomainWriteAl.createJobDomain(createDomainRequest);
    }


}
