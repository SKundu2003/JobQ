package com.jobq.Job_server.component.Job.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JobInfoDetailsResponse {

    //only there few fields are used in the response --> title, company_name, location, domain_name, job_type, salary_rage_to, salary_rage_from, experience, skill_names, is_deleted

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("location")
    private String location;

    @JsonProperty("domain_name")
    private String domainName;

    @JsonProperty("job_type")
    private String jobType;

    @JsonProperty("salary_rage_to")
    private String salaryRageTo;

    @JsonProperty("salary_rage_from")
    private String salaryRageFrom;

    @JsonProperty("experience")
    private String experience;

    @JsonProperty("skill_names")
    private String skillNames;

    @JsonProperty("is_deleted")
    private Boolean isDeleted;
}
