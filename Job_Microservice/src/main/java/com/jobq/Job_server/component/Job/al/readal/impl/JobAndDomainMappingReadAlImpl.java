package com.jobq.Job_server.component.Job.al.readal.impl;

import com.jobq.Job_server.component.Job.al.readal.JobAndDomainMappingReadAl;
import com.jobq.Job_server.component.Job.entity.JobAndDomainMappingEntity;
import com.jobq.Job_server.component.Job.module.JobAndDomainMappingDetails;
import com.jobq.Job_server.component.Job.repository.JobAndDomainMappingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JobAndDomainMappingReadAlImpl implements JobAndDomainMappingReadAl {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    private final JobAndDomainMappingRepository jobAndDomainMappingRepository;

    @Autowired
    public JobAndDomainMappingReadAlImpl(JobAndDomainMappingRepository jobAndDomainMappingRepository) {
        this.jobAndDomainMappingRepository = jobAndDomainMappingRepository;
    }

    @Override
    public Optional<List<JobAndDomainMappingDetails>> fetchJobAndDomainMappingDetails(Long jobId) {
        Optional<List<JobAndDomainMappingEntity>> byJobId = jobAndDomainMappingRepository.findByJobId(jobId);
        if(!byJobId.isPresent()){
            return Optional.empty();
        }
        List<JobAndDomainMappingDetails> jobAndDomainMappingDetails = new ArrayList<>();
        for (JobAndDomainMappingEntity jobAndDomainMappingEntity : byJobId.get()) {
            JobAndDomainMappingDetails map = MODEL_MAPPER.map(jobAndDomainMappingEntity, JobAndDomainMappingDetails.class);
            jobAndDomainMappingDetails.add(map);
        }
        return Optional.of(jobAndDomainMappingDetails);
    }

}
