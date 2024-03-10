package com.jobq.Job_server.component.Job.entity;

import com.jobq.Job_server.common.utils.DateUtils;
import com.jobq.Job_server.component.Job.request.CreateJobRequest;
import jakarta.persistence.*;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Entity
@Data
@Table(name = "job")
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "company_name")
    private String companyName;

    private String description;

    private String location;

    @Column(name = "job_type")
    private String jobType;

    @Column(name = "salary_rage_to")
    private String salaryRageFrom;

    @Column(name = "salary_rage_from")
    private String salaryRageTo;

    private String experience;

    private String createdBy;

    private String createdOn;

    private String modifiedOn;

    private String deletedOn;

    private Boolean isDeleted;

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    public static JobEntity getInstance(CreateJobRequest createJobRequest){
        String createdOn  = DateUtils.getItcDate();

        JobEntity map = MODEL_MAPPER.map(createJobRequest, JobEntity.class);
        map.setCreatedOn(createdOn);
        map.setModifiedOn(createdOn);
        map.setIsDeleted(false);
        System.err.println(map);
        return map;
    }
}
