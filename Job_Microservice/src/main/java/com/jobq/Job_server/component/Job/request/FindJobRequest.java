package com.jobq.Job_server.component.Job.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FindJobRequest {

    @JsonProperty("domain_ids")
    private Long[] domainIds;

    @JsonProperty("skill_ids")
    private Long[] skillsIds;

    @JsonProperty("location")
    private String location;

    @JsonProperty("jobType")
    private String jobType; //part-time, full-time, contract

    @JsonProperty("salary_rage_to")
    private String salaryRageTo;

    @JsonProperty("salary_rage_from")
    private String salaryRageFrom;


    @JsonProperty("page_number")
    private int pageNumber;

    @JsonProperty("page_size")
    private int pageSize;
}
