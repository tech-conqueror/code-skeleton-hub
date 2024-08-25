package com.techconqueror.tool.codeskeletonhub.batch;

import com.techconqueror.tool.codeskeletonhub.repository.JobEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JobEntityRepository jobEntityRepository;

    public JobCompletionNotificationListener(JobEntityRepository jobEntityRepository) {
        this.jobEntityRepository = jobEntityRepository;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
            // Execute the query and get the count
            Long jobCount = jobEntityRepository.count();
            // Log the number of jobs
            log.info("Total number of jobs in the database: {}", jobCount);
        }
    }
}
