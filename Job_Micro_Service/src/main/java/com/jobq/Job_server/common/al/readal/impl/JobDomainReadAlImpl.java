package com.jobq.Job_server.common.al.readal.impl;

import com.jobq.Job_server.common.al.readal.JobDomainReadAl;
import com.jobq.Job_server.common.entity.JobDomainEntity;
import com.jobq.Job_server.common.models.JobDomainDetails;
import com.jobq.Job_server.common.repository.JobDomainRepository;
import com.jobq.Job_server.component.Job.entity.JobEntity;
import com.jobq.Job_server.component.Job.repository.JobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JobDomainReadAlImpl implements JobDomainReadAl {

    private final JobDomainRepository jobDomainRepository;

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    @Autowired
    public JobDomainReadAlImpl(JobDomainRepository jobDomainRepository) {
        this.jobDomainRepository = jobDomainRepository;
    }

    @Override
    public Optional<JobDomainDetails> findJobDomainById(Long id){
        Optional<JobDomainEntity> byId = jobDomainRepository.findById(id);
        if (byId.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(MODEL_MAPPER.map(byId, JobDomainDetails.class));
    }

    @Override
    public Optional<List<JobDomainDetails>> fetchAllJobDomains(String jobDomainName){
        Optional<List<JobDomainEntity>> jobDomainEntities = jobDomainRepository.fetchAllJobDomains(jobDomainName);
        if (jobDomainEntities.isEmpty()) {
            return Optional.empty();
        }
        List<JobDomainDetails> jobDomainDetails = new ArrayList<>();
        for (JobDomainEntity jobDomainEntity : jobDomainEntities.get()) {
            jobDomainDetails.add(MODEL_MAPPER.map(jobDomainEntity, JobDomainDetails.class));
        }
        return Optional.of(jobDomainDetails);
    }

}
