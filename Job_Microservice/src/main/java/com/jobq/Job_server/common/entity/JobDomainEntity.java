package com.jobq.Job_server.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "job_domain")
public class JobDomainEntity {
    @Id
    private Long id;

    @Column(name = "domain_name")
    private String domainName;
}
