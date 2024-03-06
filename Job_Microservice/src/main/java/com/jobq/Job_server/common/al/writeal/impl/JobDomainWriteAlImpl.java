package com.jobq.Job_server.common.al.writeal.impl;

import com.jobq.Job_server.common.al.writeal.JobDomainWriteAl;
import com.jobq.Job_server.common.entity.JobDomainEntity;
import com.jobq.Job_server.common.models.JobDomainDetails;
import com.jobq.Job_server.common.repository.JobDomainRepository;
import com.jobq.Job_server.common.request.CreateDomainRequest;
import com.jobq.Job_server.component.Job.repository.JobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JobDomainWriteAlImpl implements JobDomainWriteAl {

    private final JobDomainRepository jobDomainRepository;

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    @Autowired
    public JobDomainWriteAlImpl(JobDomainRepository jobDomainRepository) {
        this.jobDomainRepository = jobDomainRepository;
    }

    @Override
    public Optional<JobDomainDetails> createJobDomain(CreateDomainRequest createDomainRequest) {
        JobDomainEntity jobDomainEntity = MODEL_MAPPER.map(createDomainRequest, JobDomainEntity.class);
        JobDomainEntity save = jobDomainRepository.save(jobDomainEntity);
        return Optional.of(MODEL_MAPPER.map(save, JobDomainDetails.class));
    }


}
