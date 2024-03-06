package com.jobq.Job_server.common.al.readal.impl;

import com.jobq.Job_server.common.al.readal.SkillsReadAl;
import com.jobq.Job_server.common.entity.SkillsEntity;
import com.jobq.Job_server.common.models.SkillsDetails;
import com.jobq.Job_server.common.repository.SkillsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SkillsReadAlImpl implements SkillsReadAl {

    private final SkillsRepository skillsRepository;

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    @Autowired
    public SkillsReadAlImpl(SkillsRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }


    @Override
    public Optional<SkillsDetails> findSkillById(Long id){
        Optional<SkillsEntity> byId = skillsRepository.findById(id);
        if(!byId.isPresent()){
            return Optional.empty();
        }
        return Optional.of(MODEL_MAPPER.map(byId, SkillsDetails.class));
    }

    @Override
    public Optional<List<SkillsDetails>> findSkillByName(String name){
        Optional<List<SkillsEntity>> byName = skillsRepository.findByName(name);
        if(!byName.isPresent()){
            return Optional.empty();
        }
        List<SkillsDetails> skillsDetails = new ArrayList<>();
        for (SkillsEntity skillsEntity : byName.get()) {
            skillsDetails.add(MODEL_MAPPER.map(skillsEntity, SkillsDetails.class));
        }
        return Optional.of(skillsDetails);
    }

}
