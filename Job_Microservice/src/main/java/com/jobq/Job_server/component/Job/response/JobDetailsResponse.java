package com.jobq.Job_server.component.Job.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class JobDetailsResponse {

    @JsonProperty("jobs_info")
    private List<JobInfoDetailsResponse> jobInfoDetailsResponse;

    @JsonProperty("current_page")
    private Integer currentPage;

    @JsonProperty("no_of_pages")
    private Integer noOfPages;

    @JsonProperty("total_jobs_count")
    private Integer totalJobsCount;

}
