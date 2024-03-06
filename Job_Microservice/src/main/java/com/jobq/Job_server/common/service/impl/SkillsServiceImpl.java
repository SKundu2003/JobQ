package com.jobq.Job_server.common.service.impl;

import com.jobq.Job_server.common.al.readal.SkillsReadAl;
import com.jobq.Job_server.common.al.writeal.SkillsWriteAl;
import com.jobq.Job_server.common.models.SkillsDetails;
import com.jobq.Job_server.common.request.CreateSkillRequest;
import com.jobq.Job_server.common.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SkillsServiceImpl implements SkillsService{

    private final SkillsReadAl skillsReadAl;

    private final SkillsWriteAl skillsWriteAl;

    @Autowired
    public SkillsServiceImpl(SkillsReadAl skillsReadAl, SkillsWriteAl skillsWriteAl) {
        this.skillsReadAl = skillsReadAl;
        this.skillsWriteAl = skillsWriteAl;
    }

    @Override
    public Optional<List<SkillsDetails>> fetchAllSkills(String name) {
        return skillsReadAl.findSkillByName(name);
    }

    @Override
    public Optional<SkillsDetails> createSkill(CreateSkillRequest createSkillRequest) {
        return skillsWriteAl.createSkill(createSkillRequest);
    }
}
