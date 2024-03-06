package com.jobq.Job_server.common.repository;

import com.jobq.Job_server.common.entity.JobDomainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobDomainRepository extends JpaRepository<JobDomainEntity, Long> {

    String FETCH_ALL_JOB_DOMAINS_BY_NAME = "SELECT * FROM job_domain WHERE domain_name like concat('%', :jobDomainName,'%') ;";

    @Query(value = FETCH_ALL_JOB_DOMAINS_BY_NAME, nativeQuery = true)
    Optional<List<JobDomainEntity>> fetchAllJobDomains(@Param("jobDomainName") String jobDomainName);
}
