package com.jobq.Job_server.common.al.writeal.impl;

import com.jobq.Job_server.common.al.writeal.SkillsWriteAl;
import com.jobq.Job_server.common.entity.SkillsEntity;
import com.jobq.Job_server.common.models.SkillsDetails;
import com.jobq.Job_server.common.repository.SkillsRepository;
import com.jobq.Job_server.common.request.CreateSkillRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SkillsWriteAlImpl implements SkillsWriteAl{
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    private final SkillsRepository skillsRepository;

    @Autowired
    public SkillsWriteAlImpl(SkillsRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }

    @Override
    public Optional<SkillsDetails> createSkill(CreateSkillRequest createSkillRequest){
        SkillsEntity skillsEntity =MODEL_MAPPER.map(createSkillRequest, SkillsEntity.class);
        SkillsEntity save = skillsRepository.save(skillsEntity);

        return Optional.of(MODEL_MAPPER.map(save, SkillsDetails.class));
    }
}
