package com.jobq.Job_server.common.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SkillsDetails {
    private Long id;

    @JsonProperty("skill_name")
    private String skillName;

    @JsonProperty("domain_id")
    private Long domainId;
}
