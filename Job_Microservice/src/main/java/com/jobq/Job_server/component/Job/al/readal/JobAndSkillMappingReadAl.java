package com.jobq.Job_server.component.Job.al.readal;

import com.jobq.Job_server.component.Job.module.JobAndSkillMappingDetails;

import java.util.List;
import java.util.Optional;

public interface JobAndSkillMappingReadAl {

    Optional<List<JobAndSkillMappingDetails>> fetchJobAndSkillMappingByJobId(Long jobId);


}
