package com.jobq.Job_server.component.Job.module;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JobAndSkillMappingDetails {

    private Long id;

    @JsonProperty("job_id")
    private Long jobId;

    @JsonProperty("skill_id")
    private Long skillId;
}
