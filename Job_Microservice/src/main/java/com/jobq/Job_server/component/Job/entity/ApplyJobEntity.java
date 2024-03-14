package com.jobq.Job_server.component.Job.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "apply_job")
@Data
public class ApplyJobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "user_id")
    private Long userId;
}
