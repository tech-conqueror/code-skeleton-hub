package com.techconqueror.tool.codeskeletonhub.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techconqueror.tool.codeskeletonhub.batch.JobCompletionNotificationListener;
import com.techconqueror.tool.codeskeletonhub.batch.JobItem;
import com.techconqueror.tool.codeskeletonhub.batch.JobItemProcessor;
import com.techconqueror.tool.codeskeletonhub.entity.JobEntity;
import com.techconqueror.tool.codeskeletonhub.repository.JobEntityRepository;
import com.techconqueror.tool.codeskeletonhub.service.ChatCompletionService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

//@Configuration
public class BatchConfiguration {

    @Bean
    public FlatFileItemReader<JobItem> reader() {
        return new FlatFileItemReaderBuilder<JobItem>()
                .name("jobItemReader")
                .resource(new ClassPathResource("job.csv"))
                .linesToSkip(1) // Ignore csv header
                .delimited()
                .names("seq", "job_id", "description")
                .targetType(JobItem.class)
                .recordSeparatorPolicy(new DefaultRecordSeparatorPolicy())
                .build();
    }

    @Bean
    public JobItemProcessor processor(final ChatCompletionService chatCompletionService, final ObjectMapper objectMapper) {
        return new JobItemProcessor(chatCompletionService, objectMapper);
    }

    @Bean
    public RepositoryItemWriter<JobEntity> writer(JobEntityRepository jobEntityRepository) {
        return new RepositoryItemWriterBuilder<JobEntity>()
                .repository(jobEntityRepository)
                .methodName("save")
                .build();
    }

    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
        return new JobBuilder("importUserJob", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }

    @Bean
    public Step mainStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                         FlatFileItemReader<JobItem> reader, JobItemProcessor processor, RepositoryItemWriter<JobEntity> writer) {
        return new StepBuilder("mainStep", jobRepository)
                .<JobItem, JobEntity>chunk(50, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
