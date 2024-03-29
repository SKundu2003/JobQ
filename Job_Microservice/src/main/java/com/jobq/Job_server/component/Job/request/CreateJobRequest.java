package com.jobq.Job_server.component.Job.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateJobRequest {

        private String title;

        private String description;

        private String companyName;

        private String location;

        private List<Long> domainIdsList;

        private String type;

        private String salaryRageFrom;

        private String salaryRageTo;

        private String experience;

        private List<Long> skillIdsList;


        private Long createdBy = 1L;

        private String createdOn;

        private String modifiedOn;

        private String deletedOn;

        private Boolean isDeleted;
}
