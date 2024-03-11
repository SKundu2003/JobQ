package com.jobq.Job_server.component.Job.repository;

import com.jobq.Job_server.component.Job.entity.JobAndDomainMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobAndDomainMappingRepository extends JpaRepository<JobAndDomainMappingEntity, Long> {

        interface Meta{
            String TABLE_NAME = "job_and_domain_mapping";
        }

        String FIND_BY_JOB_ID = "SELECT * FROM " + Meta.TABLE_NAME + " WHERE job_id = :jobId";

        @Query(value = FIND_BY_JOB_ID, nativeQuery = true)
        Optional<List<JobAndDomainMappingEntity>> findByJobId(@Param("jobId") Long jobId);
}
