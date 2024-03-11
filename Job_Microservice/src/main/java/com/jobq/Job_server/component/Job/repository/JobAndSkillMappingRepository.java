package com.jobq.Job_server.component.Job.repository;

import com.jobq.Job_server.component.Job.entity.JobAndSkillMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobAndSkillMappingRepository extends JpaRepository<JobAndSkillMappingEntity, Long>{

    interface Meta{
        String TABLE_NAME = "job_and_skill_mapping";
    }

    String FIND_BY_JOB_ID = "SELECT * FROM " + Meta.TABLE_NAME + " WHERE job_id = :jobId";

    Optional<List<JobAndSkillMappingEntity>> findByJobId(@Param("jobId") Long jobId);
}
