package com.jobq.Job_server.component.Job.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobq.Job_server.common.utils.DateUtils;
import com.jobq.Job_server.component.Job.request.CreateJobRequest;
import jakarta.persistence.*;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Arrays;

@Entity
@Data
@Table(name = "job")
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "company_name")
    private String companyName;

    private String description;

    private String location;

    @Column(name = "domain_id")
    private Long domainId;

    @Column(name = "domain_names")
    private String domainNames; // IT, HR, etc

    @Column(name = "job_type")
    private String jobType;

    @Column(name = "salary_rage_to")
    private String salaryRageFrom;

    @Column(name = "salary_rage_from")
    private String salaryRageTo;

    private String experience;

    @Column(name = "skill_ids")
    private String skillIds;

    @Column(name = "skill_names")
    private String skillNames;

    private String createdBy;

    private String createdOn;

    private String modifiedOn;

    private String deletedOn;

    private Boolean isDeleted;

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    public static JobEntity getInstance(CreateJobRequest createJobRequest){
        JobEntity map = MODEL_MAPPER.map(createJobRequest, JobEntity.class);
        String createdOn  = DateUtils.getUtcDate();
        String skillIds = Arrays.toString(createJobRequest.getSkillIds());
        skillIds = skillIds.replaceAll("\\[", "").replaceAll("\\]", "");

        String domainIds = Arrays.toString(createJobRequest.getDomainId());
        domainIds = domainIds.replaceAll("\\[", "").replaceAll("\\]", "");

        map.setDomainId(Long.parseLong(domainIds));
        map.setSkillIds(skillIds);
        map.setCreatedOn(createdOn);
        map.setModifiedOn(createdOn);
        return map;
    }
}
