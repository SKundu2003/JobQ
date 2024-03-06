package com.jobq.Job_server.component.Job.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateJobRequest {

        private String title;

        private String description;

        private String location;

        private Long[] domainId;

        private String domainNames;

        private String type;

        private String salaryRageFrom;

        private String salaryRageTo;

        private String experience;

        private Long[] skillIds;

        private String skillNames;

        private String createdBy = "1";

        private String createdOn;

        private String modifiedOn;

        private String deletedOn;

        private Boolean isDeleted;
}
