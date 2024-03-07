package com.jobq.Job_server.component.Job.service.impl;

import com.jobq.Job_server.common.al.readal.JobDomainReadAl;
import com.jobq.Job_server.common.al.readal.SkillsReadAl;
import com.jobq.Job_server.common.al.writeal.JobDomainWriteAl;
import com.jobq.Job_server.common.al.writeal.SkillsWriteAl;
import com.jobq.Job_server.common.models.JobDomainDetails;
import com.jobq.Job_server.common.models.SkillsDetails;
import com.jobq.Job_server.component.Job.al.readal.JobReadAl;
import com.jobq.Job_server.component.Job.al.writeal.JobWriteAl;
import com.jobq.Job_server.component.Job.module.JobDetails;
import com.jobq.Job_server.component.Job.request.CreateJobRequest;
import com.jobq.Job_server.component.Job.request.FindJobRequest;
import com.jobq.Job_server.component.Job.response.CompleteJobDetailsResponse;
import com.jobq.Job_server.component.Job.response.JobDetailsResponse;
import com.jobq.Job_server.component.Job.response.JobInfoDetailsResponse;
import com.jobq.Job_server.component.Job.service.JobService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    private final JobWriteAl jobWriteAl;

    private final JobReadAl jobReadAl;

    private final SkillsReadAl skillsReadAl;

    private final SkillsWriteAl skillsWriteAl;

    private final JobDomainReadAl jobDomainReadAl;

    private final JobDomainWriteAl jobDomainWriteAl;


    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(JobServiceImpl.class);

    @Autowired
    public JobServiceImpl(JobWriteAl jobWriteAl, JobReadAl jobReadAl,
                          SkillsReadAl skillsReadAl, SkillsWriteAl skillsWriteAl,
                          JobDomainReadAl jobDomainReadAl, JobDomainWriteAl jobDomainWriteAl) {
        this.jobWriteAl = jobWriteAl;
        this.jobReadAl = jobReadAl;
        this.skillsReadAl = skillsReadAl;
        this.skillsWriteAl = skillsWriteAl;
        this.jobDomainReadAl = jobDomainReadAl;
        this.jobDomainWriteAl = jobDomainWriteAl;

    }


    private Optional<Integer> countRelevantJobs(Long[] domainIds, Long[] skills, String location, String jobType, String salaryRageFrom, String salaryRageTo, int pageSize, int offset){

        return jobReadAl.countRelevantJobs(domainIds, skills, location, jobType, salaryRageFrom, salaryRageTo, pageSize, offset);
    }

    @Override
    public Optional<JobDetailsResponse> fetchRelevantJobs(FindJobRequest jobRequest){
        Long[] domainIds = jobRequest.getDomainIds();
        Long[] skills = jobRequest.getSkillsIds();
        String location = jobRequest.getLocation();
        String jobType = jobRequest.getJobType();
        String salaryRageFrom = jobRequest.getSalaryRageFrom();
        String salaryRageTo = jobRequest.getSalaryRageTo();
        int pageSize = jobRequest.getPageSize();
        int offset = (jobRequest.getPageNumber() - 1) * pageSize;

        Optional<List<JobDetails>> jobDetails = jobReadAl.fetchRelevantJobs(domainIds, skills, location, jobType, salaryRageFrom, salaryRageTo, pageSize, offset);
        if(!jobDetails.isPresent()){
            LOGGER.error("No relevant jobs found");
            return Optional.empty();
        }
        Optional<Integer> countOfJobs = countRelevantJobs(domainIds, skills, location, jobType, salaryRageFrom, salaryRageTo, pageSize, offset);
        if(!countOfJobs.isPresent()){
            LOGGER.error("Unable to count relevant jobs");
            return Optional.empty();
        }
        List<JobInfoDetailsResponse> jobInfoDetailsResponseList = jobDetails.get().stream().map(jobDetail -> MODEL_MAPPER.map(jobDetail, JobInfoDetailsResponse.class)).toList();

        JobDetailsResponse jobDetailsResponse = new JobDetailsResponse();
        jobDetailsResponse.setJobInfoDetailsResponse(jobInfoDetailsResponseList);
        jobDetailsResponse.setTotalJobsCount(countOfJobs.get());
        jobDetailsResponse.setCurrentPage(jobRequest.getPageNumber());
        jobDetailsResponse.setNoOfPages((int) Math.ceil((double) countOfJobs.get() / pageSize));
        return Optional.of(jobDetailsResponse);
    }

    @Override
    public Optional<CompleteJobDetailsResponse> fetchJobById(Long id){
        Optional<JobDetails> jobDetails = jobReadAl.fetchJobById(id);
        if(!jobDetails.isPresent()){
            LOGGER.error("No job found with id: " + id);
            return Optional.empty();
        }
        return Optional.of(MODEL_MAPPER.map(jobDetails.get(), CompleteJobDetailsResponse.class));
    }

    @Override
    public Optional<JobDetails> createJob(CreateJobRequest createJobRequest){
        StringBuilder skillNames = new StringBuilder();
        for (Long skillId : createJobRequest.getSkillIdsList()) {
            Optional<SkillsDetails> skillById = skillsReadAl.findSkillById(skillId);
            if(!skillById.isPresent()){
                LOGGER.error("No skill found with id: " + skillId);
                return Optional.empty();
            }
            skillNames.append(skillById.get().getSkillName());
            skillNames.append(",");
        }
        skillNames.deleteCharAt(skillNames.length() - 1);
        createJobRequest.setSkillNames(skillNames.toString());

        StringBuilder domainNames = new StringBuilder();
        for (Long domainId : createJobRequest.getDomainIdsList()) {
            Optional<JobDomainDetails> jobDomainById = jobDomainReadAl.findJobDomainById(domainId);
            if(!jobDomainById.isPresent()){
                LOGGER.error("No domain found with id: " + domainId);
                return Optional.empty();
            }
            domainNames.append(jobDomainById.get().getDomainName());
            domainNames.append(",");
        }
        domainNames.deleteCharAt(domainNames.length() - 1);
        createJobRequest.setDomainName(domainNames.toString());

        return jobWriteAl.createJob(createJobRequest);
    }


}


