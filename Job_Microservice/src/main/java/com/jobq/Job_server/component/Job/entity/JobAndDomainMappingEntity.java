package com.jobq.Job_server.component.Job.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "job_and_domain_mapping")
public class JobAndDomainMappingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "domain_id")
    private Long domainId;
}
