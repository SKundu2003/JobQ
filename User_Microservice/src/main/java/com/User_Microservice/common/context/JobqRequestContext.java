package com.User_Microservice.common.context;

public class JobqRequestContext {

    private static final ThreadLocal<JobqRequestContext> holder = new ThreadLocal<>();

    private Long userId;

    public JobqRequestContext() {
    }

    public static void setJobqRequestContext(JobqRequestContext context) {
        holder.set(context);
    }

    public static JobqRequestContext getJobqRequestContext() {
        return holder.get();
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

}
