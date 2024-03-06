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
    }

    String FETCH_INFO_OF_RELEVANT_JOBS = "SELECT * FROM "+Meta.TABLE_NAME +
            " WHERE is_deleted = false " +
            " AND (:domainIds IS NULL OR domain_id IN :domainIds) " +
            " AND (:skills IS NULL OR skill_ids in :skills) " +
            " AND (:location IS NULL OR location like concat('%',:location,'%')) " +
            " AND (:jobType IS NULL OR job_type LIKE CONCAT('%', :jobType, '%')) " +
            " AND (:salaryRageFrom IS NULL OR :salaryRageFrom >= salary_rage_from ) " +
            " AND (:salaryRageTo IS NULL OR :salaryRageTo <= salary_rage_to ) " +
            " ORDER BY created_on DESC " +
            " LIMIT :pageSize OFFSET :offset";


    String COUNT_RELEVANT_JOBS = "SELECT count(*) FROM "+Meta.TABLE_NAME +
            " WHERE is_deleted = false " +
            " AND (:domainIds IS NULL OR domain_id IN :domainIds) " +
            " AND (:skills IS NULL OR skill_ids in :skills) " +
            " AND (:location IS NULL OR location like concat('%',:location,'%')) " +
            " AND (:jobType IS NULL OR job_type LIKE CONCAT('%', :jobType, '%')) " +
            " AND (:salaryRageFrom IS NULL OR :salaryRageFrom >= salary_rage_from ) " +
            " AND (:salaryRageTo IS NULL OR :salaryRageTo <= salary_rage_to ) " +
            " ORDER BY created_on DESC " +
            " LIMIT :pageSize OFFSET :offset";


    String FETCH_JOB_BY_ID = "SELECT * FROM " + Meta.TABLE_NAME + " WHERE id = :id AND is_deleted = false";

    @Query(value = FETCH_INFO_OF_RELEVANT_JOBS, nativeQuery = true)
    Optional<List<JobEntity>> fetchRelevantJobs(@Param("domainIds") Long[] domainIds, @Param("skills") Long[] skillIds,
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
