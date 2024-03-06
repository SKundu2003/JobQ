package com.jobq.Job_server.common.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateSkillRequest {

        @JsonProperty("skill_name")
        private String skillName;

        @JsonProperty("domain_id")
        private Long domainId;
}
