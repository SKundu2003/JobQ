package com.jobq.Job_server.component.Job.al.writeal.impl;

import com.jobq.Job_server.component.Job.al.writeal.JobWriteAl;
import com.jobq.Job_server.component.Job.entity.JobAndDomainMappingEntity;
import com.jobq.Job_server.component.Job.entity.JobAndSkillMappingEntity;
import com.jobq.Job_server.component.Job.entity.JobEntity;
import com.jobq.Job_server.component.Job.module.JobDetails;
import com.jobq.Job_server.component.Job.repository.JobAndDomainMappingRepository;
import com.jobq.Job_server.component.Job.repository.JobAndSkillMappingRepository;
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

    private final JobAndDomainMappingRepository jobAndDomainMappingRepository;

    private final JobAndSkillMappingRepository jobAndSkillMappingRepository;



    @Autowired
    public JobWriteAlImpl(JobRepository jobRepository, JobAndDomainMappingRepository jobAndDomainMappingRepository,
                          JobAndSkillMappingRepository jobAndSkillMappingRepository) {
        this.jobRepository = jobRepository;
        this.jobAndDomainMappingRepository = jobAndDomainMappingRepository;
        this.jobAndSkillMappingRepository = jobAndSkillMappingRepository;
    }


    @Override
    public Optional<JobDetails> createJob(CreateJobRequest createJobRequest) {
        JobEntity jobEntity = JobEntity.getInstance(createJobRequest);
        Optional<JobEntity> save = Optional.of(jobRepository.save(jobEntity));
        if(!save.isPresent()){
            return Optional.empty();
        }
        JobAndSkillMappingEntity jobAndSkillMappingEntity = new JobAndSkillMappingEntity();
        JobAndDomainMappingEntity jobAndDomainMappingEntity = new JobAndDomainMappingEntity();

        for (Long skillId : createJobRequest.getSkillIdsList()) {
            jobAndSkillMappingEntity.setJobId(save.get().getId());
            jobAndSkillMappingEntity.setSkillId(skillId);
            jobAndSkillMappingRepository.save(jobAndSkillMappingEntity);
        }

        for (Long domainId : createJobRequest.getDomainIdsList()) {
            jobAndDomainMappingEntity.setJobId(save.get().getId());
            jobAndDomainMappingEntity.setDomainId(domainId);
            jobAndDomainMappingRepository.save(jobAndDomainMappingEntity);
        }

        return Optional.ofNullable(MODEL_MAPPER.map(save, JobDetails.class));

    }
}
