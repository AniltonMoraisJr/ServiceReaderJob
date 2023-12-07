package com.example.serviceReaderJob.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ServiceReaderJobConfig {

    @Bean
    public Job serviceReaderJob(JobRepository jobRepository, Step serviceReaderStep) {
        return new JobBuilder("serviceReaderJob", jobRepository)
                .start(serviceReaderStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
