package com.jobq.Job_server.component.Job.repository;

import com.jobq.Job_server.component.Job.entity.JobAndSkillMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobAndSkillMappingRepository extends JpaRepository<JobAndSkillMappingEntity, Long>{

    interface Meta{
        String TABLE_NAME = "job_and_skill_mapping";
    }
}
