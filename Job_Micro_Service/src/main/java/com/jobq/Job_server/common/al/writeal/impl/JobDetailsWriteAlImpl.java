package com.jobq.Job_server.common.al.writeal.impl;

import com.jobq.Job_server.common.al.writeal.JobDetailsWriteAl;
import com.jobq.Job_server.component.Job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobDetailsWriteAlImpl implements JobDetailsWriteAl {

    private final JobRepository jobRepository;

    @Autowired
    public JobDetailsWriteAlImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
}
