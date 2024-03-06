package com.jobq.Job_server.common.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class JobDomainDetails {
    private Long id;

    @JsonProperty("domain_name")
    private String domainName;
}
