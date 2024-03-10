package com.jobq.Job_server.component.Job.repository;


import com.jobq.Job_server.component.Job.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<JobEntity, Long> {

    interface Meta{
        String JOB_TABLE_NAME = " job j ";
        String DOMAIN_TABLE_NAME = " job_domain jd ";

        String SKILL_TABLE_NAME = " skills s ";

        String JOB_AND_SKILL_MAPPING_TABLE_NAME = " job_and_skill_mapping jsm ";

        String JOB_AND_DOMAIN_MAPPING_TABLE_NAME = " job_and_domain_mapping jdm ";
        String SHORT_INFO_OF_JOB_COLUMNS = " j.id, j.title, j.company_name, j.location, j.job_type, j.salary_rage_from, j.salary_rage_to, j.created_on, s.skill_name, jd.domain_name ";

        String CONDITION_FOR_RELEVANT_JOBS = "j.is_deleted = false"+
                " AND (case when :domainIds is null then true else j.id in (select jdm.job_id from "+JOB_AND_DOMAIN_MAPPING_TABLE_NAME+" where jdm.domain_id in (:domainIds)) end)"+
                " AND (case when :skills is null then true else j.id in (select jsm.job_id from "+JOB_AND_SKILL_MAPPING_TABLE_NAME+" where jsm.skill_id in (:skills)) end)"+
                " AND (case when :location is null then true else j.location like %:location%) end"+
                " AND (case when :jobType is null then true else j.job_type = :jobType end)"+
                " AND (case when :salaryRageTo is null then true else j.salary_rage_to >= :salaryRageTo end)"+
                " AND (case when :salaryRageFrom is null then true else j.salary_rage_from <= :salaryRageFrom end)";



    }

    String FETCH_INFO_OF_RELEVANT_JOBS = "SELECT "+ Meta.SHORT_INFO_OF_JOB_COLUMNS+ " FROM "+Meta.JOB_TABLE_NAME + Meta.JOB_AND_SKILL_MAPPING_TABLE_NAME + Meta.JOB_AND_DOMAIN_MAPPING_TABLE_NAME + Meta.SKILL_TABLE_NAME + Meta.DOMAIN_TABLE_NAME +
            " WHERE "+Meta.CONDITION_FOR_RELEVANT_JOBS +
            " ORDER BY created_on DESC " +
            " LIMIT :pageSize OFFSET :offset";


    String COUNT_RELEVANT_JOBS = "SELECT "+ Meta.SHORT_INFO_OF_JOB_COLUMNS+ " FROM " +Meta.JOB_TABLE_NAME + Meta.JOB_AND_SKILL_MAPPING_TABLE_NAME + Meta.JOB_AND_DOMAIN_MAPPING_TABLE_NAME + Meta.SKILL_TABLE_NAME + Meta.DOMAIN_TABLE_NAME +
            " WHERE "+Meta.CONDITION_FOR_RELEVANT_JOBS +
            " ORDER BY created_on DESC ";


    String FETCH_JOB_BY_ID = "SELECT "+ Meta.SHORT_INFO_OF_JOB_COLUMNS+ " FROM " +Meta.JOB_TABLE_NAME + Meta.JOB_AND_SKILL_MAPPING_TABLE_NAME + Meta.JOB_AND_DOMAIN_MAPPING_TABLE_NAME + Meta.SKILL_TABLE_NAME + Meta.DOMAIN_TABLE_NAME +
            " WHERE j.id = :id AND j.is_deleted = false";

    @Query(value = FETCH_INFO_OF_RELEVANT_JOBS, nativeQuery = true)
    Optional<List<JobEntity>> fetchRelevantJobs(@Param("domainIds") Long[] domainIds, @Param("skills") Long[] skillIds,
                                                @Param("location") String location, @Param("jobType") String jobType,
                                                @Param("salaryRageFrom") String salaryRageFrom, @Param("salaryRageTo") String salaryRageTo,
                                                @Param("pageSize") int pageSize, @Param("offset") int offset);


    @Query(value = COUNT_RELEVANT_JOBS, nativeQuery = true)
    Optional<Integer> countRelevantJobs(@Param("domainIds") Long[] domainIds, @Param("skills") Long[] skills,
                                       @Param("location") String location, @Param("jobType") String jobType,
                                       @Param("salaryRageFrom") String salaryRageFrom, @Param("salaryRageTo") String salaryRageTo);

    @Query(value = FETCH_JOB_BY_ID, nativeQuery = true)
    Optional<JobEntity> fetchJobById(@Param("id") Long id);



}
