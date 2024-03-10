package com.jobq.Job_server.component.Job.repository;

import com.jobq.Job_server.component.Job.entity.JobAndDomainMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobAndDomainMappingRepository extends JpaRepository<JobAndDomainMappingEntity, Long> {

        interface Meta{
            String TABLE_NAME = "job_and_domain_mapping";
        }
}
