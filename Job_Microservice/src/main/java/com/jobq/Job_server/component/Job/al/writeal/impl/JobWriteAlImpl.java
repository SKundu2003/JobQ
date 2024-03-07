package com.jobq.Job_server.component.Job.al.writeal.impl;

import com.jobq.Job_server.component.Job.al.writeal.JobWriteAl;
import com.jobq.Job_server.component.Job.entity.JobEntity;
import com.jobq.Job_server.component.Job.module.JobDetails;
import com.jobq.Job_server.component.Job.repository.JobRepository;
import com.jobq.Job_server.component.Job.request.CreateJobRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JobWriteAlImpl implements JobWriteAl {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    private final JobRepository jobRepository;

    @Autowired
    public JobWriteAlImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    @Override
    public Optional<JobDetails> createJob(CreateJobRequest createJobRequest) {
        JobEntity jobEntity = JobEntity.getInstance(createJobRequest);
        JobEntity save = jobRepository.save(jobEntity);
        return Optional.ofNullable(MODEL_MAPPER.map(save, JobDetails.class));

    }
}
