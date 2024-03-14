package com.jobq.Job_server.component.Job.al.writeal.impl;

import com.jobq.Job_server.component.Job.al.writeal.ApplyJobWriteAl;
import com.jobq.Job_server.component.Job.entity.ApplyJobEntity;
import com.jobq.Job_server.component.Job.repository.ApplyJobRepository;
import com.jobq.Job_server.component.Job.repository.JobRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplyJobWriteAlImpl implements ApplyJobWriteAl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyJobWriteAlImpl.class);
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

   private final ApplyJobRepository applyJobRepository;
   @Autowired

    public ApplyJobWriteAlImpl(ApplyJobRepository applyJobRepository) {
        this.applyJobRepository = applyJobRepository;
    }


    @Override
    public Optional<Boolean> applyJob(Long userId,Long jobId) {
        Optional<ApplyJobEntity> applicationCheck = applyJobRepository.checkIfUserApplied(userId, jobId);
        if(applicationCheck.isPresent()){
            LOGGER.error("User has already applied for the job");
            return Optional.empty();
        }

        ApplyJobEntity applyJobEntity = new ApplyJobEntity();
        applyJobEntity.setJobId(jobId);
        applyJobEntity.setUserId(userId);

        applyJobRepository.save(applyJobEntity);
        LOGGER.info("User applied for the job");
        return Optional.of(true);




    }


}
