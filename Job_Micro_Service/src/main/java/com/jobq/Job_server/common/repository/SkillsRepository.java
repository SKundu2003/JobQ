package com.jobq.Job_server.common.repository;

import com.jobq.Job_server.common.entity.SkillsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SkillsRepository extends JpaRepository<SkillsEntity, Long> {

    String FIND_SKILLS_BY_NAME = "SELECT * FROM skills WHERE skill_name like concat('%',:name,'%')";

    @Query(value = FIND_SKILLS_BY_NAME, nativeQuery = true)
    Optional<List<SkillsEntity>> findByName(String name);
}
