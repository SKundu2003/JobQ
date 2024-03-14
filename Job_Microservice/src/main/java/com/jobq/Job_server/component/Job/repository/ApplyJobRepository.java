package com.jobq.Job_server.component.Job.repository;

import com.jobq.Job_server.component.Job.entity.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, Long>{

    //query to check if the user has already applied for the job
    interface Meta{
        String TABLE_NAME = "apply_job";
    }

    String CHECK_IF_USER_APPLIED = "SELECT * FROM "+Meta.TABLE_NAME+" WHERE user_id = :userId AND job_id = :jobId";



    @Query(value = CHECK_IF_USER_APPLIED, nativeQuery = true)
    Optional<ApplyJobEntity> checkIfUserApplied(@Param("userId") Long userId, @Param("jobId") Long jobId);
}
