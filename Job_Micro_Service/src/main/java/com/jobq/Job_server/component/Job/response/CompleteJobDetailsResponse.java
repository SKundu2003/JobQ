package com.jobq.Job_server.component.Job.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) //to ignore unknown fields
@JsonInclude(JsonInclude.Include.NON_NULL) //to ignore null fields
public class CompleteJobDetailsResponse {

    private Long id;

    private String title;

    @JsonProperty("company_name")
    private String companyName;

    private String description;

    private String location;

    @JsonProperty("domain_id")
    private Long domainId;

    @JsonProperty("domain_name")
    private String domainName; // IT, HR, etc

    private String type; //part-time, full-time, contract

    @JsonProperty("salary_rage_to")
    private String salaryRageTo;

    @JsonProperty("salary_rage_from")
    private String salaryRageFrom;

    private String experience;

    @JsonProperty("skill_ids")
    private String skillIds;

    @JsonProperty("skill_names")
    private String skillNames;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("created_on")
    private String createdOn;

    @JsonProperty("modified_on")
    private String modifiedOn;

    private String deletedOn;

    @JsonProperty("is_deleted")
    private Boolean isDeleted;
}
