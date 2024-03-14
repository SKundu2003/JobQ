package com.jobq.Job_server.component.Job.al.writeal;

import java.util.Optional;

public interface ApplyJobWriteAl {
    Optional<Boolean> applyJob(Long userId,Long jobId);
}
