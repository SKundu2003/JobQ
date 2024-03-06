package com.jobq.Job_server.common.controllers;

import com.jobq.Job_server.common.request.CreateSkillRequest;
import com.jobq.Job_server.common.response.handler.ResponseHandler;
import com.jobq.Job_server.common.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skill")
public class SkillController {
    private final SkillsService skillService;

    @Autowired
    public SkillController(SkillsService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<?> fetchSkills(@RequestParam String name) {
        return ResponseHandler.generateResponse("success", "Skills fetched successfully",
                skillService.fetchAllSkills(name), org.springframework.http.HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSkill(@RequestBody CreateSkillRequest createSkillRequest) {
        return ResponseHandler.generateResponse("success", "Skill created successfully",
                skillService.createSkill(createSkillRequest), org.springframework.http.HttpStatus.OK);
    }
}
