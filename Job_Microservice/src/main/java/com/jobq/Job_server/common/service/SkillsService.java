package com.jobq.Job_server.common.service;

import com.jobq.Job_server.common.models.SkillsDetails;
import com.jobq.Job_server.common.request.CreateSkillRequest;

import java.util.List;
import java.util.Optional;

public interface SkillsService {

    Optional<List<SkillsDetails>> fetchAllSkills(String name);

    Optional<SkillsDetails> findSkillById(Long id);

    Optional<SkillsDetails> createSkill(CreateSkillRequest createSkillRequest);
}
