package com.jobq.Job_server.common.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "skills")
public class SkillsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "skill_name")
    private String skillName;

    @Column(name = "domain_id")
    private Long domainId;

}
