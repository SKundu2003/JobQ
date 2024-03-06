package com.jobq.Job_server.common.al.readal;

import com.jobq.Job_server.common.models.SkillsDetails;

import java.util.List;
import java.util.Optional;

public interface SkillsReadAl {

    Optional<SkillsDetails> findSkillById(Long id);

    Optional<List<SkillsDetails>> findSkillByName(String name);

}
