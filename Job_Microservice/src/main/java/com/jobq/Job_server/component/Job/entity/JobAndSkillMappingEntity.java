package com.jobq.Job_server.component.Job.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "job_and_skill_mapping")
@Data
public class JobAndSkillMappingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "skill_id")
    private Long skillId;
}
