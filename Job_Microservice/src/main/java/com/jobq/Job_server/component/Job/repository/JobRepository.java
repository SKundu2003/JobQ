package com.jobq.Job_server.component.Job.repository;


import com.jobq.Job_server.component.Job.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<JobEntity, Long> {

    interface Meta{
        String TABLE_NAME = "job";
        String SHORT_INFO_OF_JOB_COLUMNS = "title, company_name, location, domain_name, job_type, salary_rage_to, salary_rage_from, experience, skill_names, is_deleted";

        String CONDITION_FOR_RELEVANT_JOBS = "is_deleted = false " +
                " AND (CASE WHEN :domainIds IS NOT NULL THEN domain_id in :domainIds ELSE true END) " +
                " AND (CASE WHEN :skills IS NOT NULL THEN skill_ids in :skills ELSE true END) " +
                " AND (CASE WHEN :location IS NOT NULL THEN location = :location ELSE true END) " +
                " AND (CASE WHEN :jobType IS NOT NULL THEN job_type = :jobType ELSE true END) " +
                " AND (CASE WHEN :salaryRageFrom IS NOT NULL THEN salary_rage_from >= :salaryRageFrom ELSE true END) " +
                " AND (CASE WHEN :salaryRageTo IS NOT NULL THEN salary_rage_to <= :salaryRageTo ELSE true END)";
    }

    String FETCH_INFO_OF_RELEVANT_JOBS = "SELECT * FROM "+Meta.TABLE_NAME +
            " WHERE "+Meta.CONDITION_FOR_RELEVANT_JOBS +
            " ORDER BY created_on DESC " +
            " LIMIT :pageSize OFFSET :offset";


    String COUNT_RELEVANT_JOBS = "SELECT count(*) FROM "+Meta.TABLE_NAME +
            " WHERE "+Meta.CONDITION_FOR_RELEVANT_JOBS +
            " ORDER BY created_on DESC " +
            " LIMIT :pageSize OFFSET :offset";


    String FETCH_JOB_BY_ID = "SELECT * FROM " + Meta.TABLE_NAME + " WHERE id = :id AND is_deleted = false";

    @Query(value = FETCH_INFO_OF_RELEVANT_JOBS, nativeQuery = true)
    Optional<List<JobEntity>> fetchRelevantJobs(@Param("domainIds") String domainIds, @Param("skills") String skillIds,
                                                @Param("location") String location, @Param("jobType") String jobType,
                                                @Param("salaryRageFrom") String salaryRageFrom, @Param("salaryRageTo") String salaryRageTo,
                                                @Param("pageSize") int pageSize, @Param("offset") int offset);


    @Query(value = COUNT_RELEVANT_JOBS, nativeQuery = true)
    Optional<Integer> countRelevantJobs(@Param("domainIds") Long[] domainIds, @Param("skills") Long[] skills,
                                       @Param("location") String location, @Param("jobType") String jobType,
                                       @Param("salaryRageFrom") String salaryRageFrom, @Param("salaryRageTo") String salaryRageTo,
                                       @Param("pageSize") int pageSize, @Param("offset") int offset);

    @Query(value = FETCH_JOB_BY_ID, nativeQuery = true)
    Optional<JobEntity> fetchJobById(@Param("id") Long id);



}
