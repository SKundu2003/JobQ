package com.jobq.Job_server.component.Job.Dto;

import org.springframework.beans.factory.annotation.Value;

public interface JobDetailsDto {

    @Value("#{target.id}")
    Long getId();

    @Value("#{target.title}")
    String getTitle();

    @Value("#{target.description}")
    String getDescription();

    @Value("#{target.company_name}")
    String getCompanyName();

    @Value("#{target.location}")
    String getLocation();

    @Value("#{target.type}")
    String getType();

    @Value("#{target.salary_rage_from}")
    String getSalaryRageFrom();

    @Value("#{target.salary_rage_to}")
    String getSalaryRageTo();

    @Value("#{target.experience}")
    String getExperience();

    @Value("#{target.created_by}")
    String getCreatedBy();

    @Value("#{target.created_on}")
    String getCreatedOn();

    @Value("#{target.modified_on}")
    String getModifiedOn();

    @Value("#{target.deleted_on}")
    String getDeletedOn();

    @Value("#{target.is_deleted}")
    Boolean getIsDeleted();

    @Value("#{target.domain_name}")
    String getDomainName();

    @Value("#{target.skill_name}")
    String getSkillName();

    @Value("#{target.job_type}")
    String getJobType();

    @Value("#{target.domain_id}")
    Long getDomainId();

    @Value("#{target.skill_id}")
    Long getSkillId();

}
