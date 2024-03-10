package com.jobq.Job_server.component.Job.al.readal;

import com.jobq.Job_server.component.Job.module.JobDetails;

import java.util.List;
import java.util.Optional;

public interface JobReadAl {

    Optional<List<JobDetails>> fetchRelevantJobs(Long[] domainIds, Long[] skillIds, String location, String jobType, String salaryRageFrom, String salaryRageTo, int pageSize, int offset);

    Optional<Integer> countRelevantJobs(Long[] domainIds, Long[] skills, String location, String jobType, String salaryRageFrom, String salaryRageTo);

    Optional<JobDetails> fetchJobById(Long id);

}
