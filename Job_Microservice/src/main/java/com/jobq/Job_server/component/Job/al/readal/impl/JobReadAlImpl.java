package com.jobq.Job_server.component.Job.al.readal.impl;

import com.jobq.Job_server.component.Job.al.readal.JobReadAl;
import com.jobq.Job_server.component.Job.entity.JobEntity;
import com.jobq.Job_server.component.Job.module.JobDetails;
import com.jobq.Job_server.component.Job.repository.JobRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
public class JobReadAlImpl implements JobReadAl {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(JobReadAlImpl.class);

    private final JobRepository jobRepository;

    @Autowired
    public JobReadAlImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Optional<List<JobDetails>> fetchRelevantJobs(Long[] domainIds, Long[] skillIds, String location, String jobType, String salaryRageFrom, String salaryRageTo, int pageSize, int offset) {

        System.err.println("domainIds: " + Arrays.toString(domainIds));
        System.err.println("skillIds: " + Arrays.toString(skillIds));
        System.err.println("location: " + location);
        System.err.println("jobType: " + jobType);
        System.err.println("salaryRageFrom: " + salaryRageFrom);
        System.err.println("salaryRageTo: " + salaryRageTo);
        System.err.println("pageSize: " + pageSize);
        System.err.println("offset: " + offset);

        StringBuilder domainIdsStr = new StringBuilder();
        StringBuilder skillIdsStr = new StringBuilder();

        Optional<List<JobEntity>> jobEntities = jobRepository.fetchRelevantJobs(domainIds, skillIds, location, jobType, salaryRageFrom, salaryRageTo, pageSize, offset);
        if(jobEntities.isEmpty() || jobEntities.get().isEmpty()){
            LOGGER.error("No relevant jobs found");
            return Optional.empty();
        }
        LOGGER.info("job found");

        List<JobDetails> jobDetailsList = new ArrayList<>();
        for (JobEntity job : jobEntities.get()){
            JobDetails jobDetails = MODEL_MAPPER.map(job,JobDetails.class);
            jobDetailsList.add(jobDetails);
        }
        return Optional.of(jobDetailsList);
    }

    @Override
    public Optional<Integer> countRelevantJobs(Long[] domainIds, Long[] skills, String location, String jobType, String salaryRageFrom, String salaryRageTo, int pageSize, int offset) {
        return jobRepository.countRelevantJobs(domainIds, skills, location, jobType, salaryRageFrom, salaryRageTo, pageSize, offset);
    }

    @Override
    public Optional<JobDetails> fetchJobById(Long id) {
        return Optional.ofNullable(MODEL_MAPPER.map(jobRepository.fetchJobById(id), JobDetails.class));
    }
}
