package com.jobq.Job_server.component.Job.repository;


import com.jobq.Job_server.component.Job.Dto.JobDetailsDto;
import com.jobq.Job_server.component.Job.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<JobEntity, Long> {

    interface Meta {
        String JOB_TABLE_NAME = " job j ";
        String DOMAIN_TABLE_NAME = " job_domain jd ";

        String SKILL_TABLE_NAME = " skills s ";

        String JOB_AND_SKILL_MAPPING_TABLE_NAME = " job_and_skill_mapping jsm ";

        String JOB_AND_DOMAIN_MAPPING_TABLE_NAME = " job_and_domain_mapping jdm ";
        String SHORT_INFO_OF_JOB_COLUMNS = " j.id, j.title, j.company_name, j.location, j.job_type, j.salary_rage_from, j.salary_rage_to, j.created_on, j.modified_on, j.is_deleted, j.experience, j.created_by, j.deleted_on," +
                " jd.domain_name, s.skill_name ";


        String FETCH_JOB_ID_BY_DOMAIN_ID = "SELECT jdm.job_id FROM " + JOB_AND_DOMAIN_MAPPING_TABLE_NAME + " WHERE jdm.domain_id IN :domainIds ";

        String FETCH_JOB_ID_BY_SKILL_ID = "SELECT jsm.job_id FROM " + JOB_AND_SKILL_MAPPING_TABLE_NAME + " WHERE jsm.skill_id IN :skillIds ";
        String CONDITION_FOR_RELEVANT_JOBS = "j.is_deleted = false" +
                " AND (case when :domainIds is null then true else j.id in ( " + FETCH_JOB_ID_BY_DOMAIN_ID + " ) end ) " +
                " AND (case when :skillIds is null then true else j.id in ( " + FETCH_JOB_ID_BY_SKILL_ID + " ) end ) " +
                " AND (case when :location is null then true else j.location like %:location% end ) " +
                " AND (case when :salaryRageFrom is null then true else j.salary_rage_from >= :salaryRageFrom end ) " +
                " AND (case when :salaryRageTo is null then true else j.salary_rage_to <= :salaryRageTo end ) ";


    }

    String FETCH_INFO_OF_RELEVANT_JOBS = "SELECT " + Meta.SHORT_INFO_OF_JOB_COLUMNS + " FROM " + Meta.JOB_TABLE_NAME + " JOIN " +
            Meta.JOB_AND_SKILL_MAPPING_TABLE_NAME + " ON j.id = jsm.job_id JOIN " +
            Meta.JOB_AND_DOMAIN_MAPPING_TABLE_NAME + " ON j.id = jdm.job_id JOIN " +
            Meta.SKILL_TABLE_NAME + " ON jsm.skill_id = s.id JOIN " +
            Meta.DOMAIN_TABLE_NAME + " ON jdm.domain_id = jd.id " +
            " WHERE " + Meta.CONDITION_FOR_RELEVANT_JOBS +
            " ORDER BY created_on DESC " +
            " LIMIT :pageSize OFFSET :offset";


    String COUNT_RELEVANT_JOBS = "SELECT COUNT(*) FROM " + Meta.JOB_TABLE_NAME + " JOIN " +
            Meta.JOB_AND_SKILL_MAPPING_TABLE_NAME + " ON j.id = jsm.job_id JOIN " +
            Meta.JOB_AND_DOMAIN_MAPPING_TABLE_NAME + " ON j.id = jdm.job_id JOIN " +
            Meta.SKILL_TABLE_NAME + " ON jsm.skill_id = s.id JOIN " +
            Meta.DOMAIN_TABLE_NAME + " ON jdm.domain_id = jd.id " +
            " WHERE " + Meta.CONDITION_FOR_RELEVANT_JOBS +
            " ORDER BY created_on DESC ";


    String FETCH_JOB_BY_ID = "SELECT " + Meta.SHORT_INFO_OF_JOB_COLUMNS + " FROM " + Meta.JOB_TABLE_NAME + " , "
            + Meta.JOB_AND_SKILL_MAPPING_TABLE_NAME + " , "
            + Meta.JOB_AND_DOMAIN_MAPPING_TABLE_NAME + " , "
            + Meta.SKILL_TABLE_NAME + " , "
            + Meta.DOMAIN_TABLE_NAME +
            " WHERE j.id = :id AND j.is_deleted = false";

    @Query(value = FETCH_INFO_OF_RELEVANT_JOBS, nativeQuery = true)
    Optional<List<JobDetailsDto>> fetchRelevantJobs(@Param("domainIds") List<Long> domainIds, @Param("skillIds") List<Long> skillIds,
                                                    @Param("location") String location, @Param("salaryRageFrom") String salaryRageFrom,
                                                    @Param("salaryRageTo") String salaryRageTo, @Param("pageSize") int pageSize,
                                                    @Param("offset") int offset);


    @Query(value = COUNT_RELEVANT_JOBS, nativeQuery = true)
    Optional<Integer> countRelevantJobs(@Param("domainIds") List<Long> domainIds, @Param("skillIds") List<Long> skillIds,
                                        @Param("location") String location, @Param("salaryRageFrom") String salaryRageFrom,
                                        @Param("salaryRageTo") String salaryRageTo);

    @Query(value = FETCH_JOB_BY_ID, nativeQuery = true)
    Optional<JobEntity> fetchJobById(@Param("id") Long id);


}
