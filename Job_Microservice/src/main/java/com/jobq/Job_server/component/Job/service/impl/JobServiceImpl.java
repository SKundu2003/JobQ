package com.jobq.Job_server.component.Job.service.impl;

import com.jobq.Job_server.common.al.readal.JobDomainReadAl;
import com.jobq.Job_server.common.al.readal.SkillsReadAl;
import com.jobq.Job_server.common.al.writeal.JobDomainWriteAl;
import com.jobq.Job_server.common.al.writeal.SkillsWriteAl;
import com.jobq.Job_server.common.models.JobDomainDetails;
import com.jobq.Job_server.common.models.SkillsDetails;
import com.jobq.Job_server.component.Job.Dto.JobDetailsDto;
import com.jobq.Job_server.component.Job.al.readal.JobAndDomainMappingReadAl;
import com.jobq.Job_server.component.Job.al.readal.JobAndSkillMappingReadAl;
import com.jobq.Job_server.component.Job.al.readal.JobReadAl;
import com.jobq.Job_server.component.Job.al.writeal.JobWriteAl;
import com.jobq.Job_server.component.Job.module.JobAndDomainMappingDetails;
import com.jobq.Job_server.component.Job.module.JobAndSkillMappingDetails;
import com.jobq.Job_server.component.Job.module.JobDetails;
import com.jobq.Job_server.component.Job.request.CreateJobRequest;
import com.jobq.Job_server.component.Job.request.FindJobRequest;
import com.jobq.Job_server.component.Job.response.CompleteJobDetailsResponse;
import com.jobq.Job_server.component.Job.response.CreateJobResponse;
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

    private final JobAndDomainMappingReadAl jobAndDomainMappingReadAl;

    private final JobAndSkillMappingReadAl jobAndSkillMappingReadAl;

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(JobServiceImpl.class);

    @Autowired
    public JobServiceImpl(JobWriteAl jobWriteAl, JobReadAl jobReadAl,
                          SkillsReadAl skillsReadAl, SkillsWriteAl skillsWriteAl,
                          JobDomainReadAl jobDomainReadAl, JobDomainWriteAl jobDomainWriteAl,
                          JobAndDomainMappingReadAl jobAndDomainMappingReadAl, JobAndSkillMappingReadAl jobAndSkillMappingReadAl) {
        this.jobWriteAl = jobWriteAl;
        this.jobReadAl = jobReadAl;
        this.skillsReadAl = skillsReadAl;
        this.skillsWriteAl = skillsWriteAl;
        this.jobDomainReadAl = jobDomainReadAl;
        this.jobDomainWriteAl = jobDomainWriteAl;
        this.jobAndDomainMappingReadAl = jobAndDomainMappingReadAl;
        this.jobAndSkillMappingReadAl = jobAndSkillMappingReadAl;

    }


    private Optional<Integer> countRelevantJobs(List<Long> domainIds, List<Long> skills, String location, String salaryRageFrom, String salaryRageTo, int pageSize, int offset){

        return jobReadAl.countRelevantJobs(domainIds, skills, location, salaryRageFrom, salaryRageTo);
    }

    @Override
    public Optional<JobDetailsResponse> fetchRelevantJobs(FindJobRequest jobRequest){
        List<Long> domainIds = jobRequest.getDomainIds();
        List<Long> skills = jobRequest.getSkillsIds();
        String location = jobRequest.getLocation();
        String jobType = jobRequest.getJobType();
        String salaryRageFrom = jobRequest.getSalaryRageFrom();
        String salaryRageTo = jobRequest.getSalaryRageTo();
        int pageSize = jobRequest.getPageSize();
        int offset = (jobRequest.getPageNumber() - 1) * pageSize;

        Optional<List<JobDetailsDto>> jobDetails = jobReadAl.fetchRelevantJobs(domainIds, skills, location, salaryRageFrom, salaryRageTo, pageSize, offset);
        if(!jobDetails.isPresent()){
            LOGGER.error("No relevant jobs found");
            return Optional.empty();
        }
        Optional<Integer> countOfJobs = countRelevantJobs(domainIds, skills, location, salaryRageFrom, salaryRageTo, pageSize, offset);
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
        Optional<JobDetailsDto> jobDetails = jobReadAl.fetchJobById(id);
        if(!jobDetails.isPresent()){
            LOGGER.error("No job found with id: " + id);
            return Optional.empty();
        }
        return Optional.of(MODEL_MAPPER.map(jobDetails.get(), CompleteJobDetailsResponse.class));
    }

    @Override
    public Optional<CreateJobResponse> createJob(CreateJobRequest createJobRequest){
        Optional<JobDetails> job = jobWriteAl.createJob(createJobRequest);
        if(!job.isPresent()){
            LOGGER.error("Job creation failed");
            return Optional.empty();
        }
        Optional<List<JobAndDomainMappingDetails>> jobAndDomainMappingDetails = jobAndDomainMappingReadAl.fetchJobAndDomainMappingDetails(job.get().getId());
        Optional<List<JobAndSkillMappingDetails>> jobAndSkillMappingDetails = jobAndSkillMappingReadAl.fetchJobAndSkillMappingByJobId(job.get().getId());

        if(!jobAndDomainMappingDetails.isPresent() || !jobAndSkillMappingDetails.isPresent()){
            LOGGER.error("No domain or skill mapping found for job id: " + job.get().getId());
            return Optional.empty();
        }
        List<JobDomainDetails> jobDomainDetails = jobAndDomainMappingDetails.get().stream().map(jobAndDomainMappingDetail -> jobDomainReadAl.findJobDomainById(jobAndDomainMappingDetail.getDomainId()).get()).toList();
        List<SkillsDetails> skillsDetails = jobAndSkillMappingDetails.get().stream().map(jobAndSkillMappingDetail -> skillsReadAl.findSkillById(jobAndSkillMappingDetail.getSkillId()).get()).toList();

        CreateJobResponse createJobResponse = MODEL_MAPPER.map(job.get(), CreateJobResponse.class);
        createJobResponse.setDomainId(jobDomainDetails.stream().map(JobDomainDetails::getId).toList());
        createJobResponse.setDomainName(jobDomainDetails.stream().map(JobDomainDetails::getDomainName).toList());
        createJobResponse.setSkillIds(skillsDetails.stream().map(SkillsDetails::getId).toList());
        createJobResponse.setSkillNames(skillsDetails.stream().map(SkillsDetails::getSkillName).toList());

        return Optional.of(createJobResponse);
    }


}


