package com.User_Microservice.common.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Data
public abstract class JobQBaseEntity {

    private Long id;

    private String createdOn;

    private String modifiedOn;

    private Long modifiedBy = 1L;

    private Long createdBy = 1L;

    private Boolean isDeleted = Boolean.FALSE;
}
