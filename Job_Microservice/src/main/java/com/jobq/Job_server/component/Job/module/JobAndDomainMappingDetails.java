package com.jobq.Job_server.component.Job.module;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JobAndDomainMappingDetails {
    private Long id;

    @JsonProperty("job_id")
    private Long jobId;

    @JsonProperty("domain_id")
    private Long domainId;
}
