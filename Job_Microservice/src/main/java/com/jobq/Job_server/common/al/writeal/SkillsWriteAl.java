package com.jobq.Job_server.common.al.writeal;

import com.jobq.Job_server.common.models.SkillsDetails;
import com.jobq.Job_server.common.request.CreateSkillRequest;

import java.util.Optional;

public interface SkillsWriteAl {

    Optional<SkillsDetails> createSkill(CreateSkillRequest createSkillRequest);
}
