package com.jobq.Job_server.component.Job.al.readal.impl;

import com.jobq.Job_server.component.Job.al.readal.JobAndSkillMappingReadAl;
import com.jobq.Job_server.component.Job.entity.JobAndSkillMappingEntity;
import com.jobq.Job_server.component.Job.module.JobAndSkillMappingDetails;
import com.jobq.Job_server.component.Job.repository.JobAndDomainMappingRepository;
import com.jobq.Job_server.component.Job.repository.JobAndSkillMappingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JobAndSkillMappingReadAlImpl implements JobAndSkillMappingReadAl {
    private final ModelMapper MODEL_MAPPER = new ModelMapper();

    private final JobAndSkillMappingRepository jobAndSkillMappingRepository;

    @Autowired
    public JobAndSkillMappingReadAlImpl(JobAndSkillMappingRepository jobAndSkillMappingRepository) {
        this.jobAndSkillMappingRepository = jobAndSkillMappingRepository;
    }

    @Override
    public Optional<List<JobAndSkillMappingDetails>> fetchJobAndSkillMappingByJobId(Long jobId) {
        Optional<List<JobAndSkillMappingEntity>> byJobId = jobAndSkillMappingRepository.findByJobId(jobId);
        if(!byJobId.isPresent()){
            return Optional.empty();
        }
        List<JobAndSkillMappingDetails> jobAndSkillMappingDetails = new ArrayList<>();
        for (JobAndSkillMappingEntity jobAndSkillMappingEntity : byJobId.get()) {
            JobAndSkillMappingDetails map = MODEL_MAPPER.map(jobAndSkillMappingEntity, JobAndSkillMappingDetails.class);
            jobAndSkillMappingDetails.add(map);
        }
        return Optional.of(jobAndSkillMappingDetails);
    }

}
