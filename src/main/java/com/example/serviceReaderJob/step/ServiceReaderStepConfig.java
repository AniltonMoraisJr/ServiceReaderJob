package com.example.serviceReaderJob.step;

import com.example.serviceReaderJob.domain.User;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ServiceReaderStepConfig {

    @Value("${chunkSize}")
    private int chunkSize;

    @Bean
    public Step serviceReaderStep(
            JobRepository jobRepository,
            ItemReader<User> reader,
            ItemWriter<User> userItemWriter,
            PlatformTransactionManager transactionManager) {

        return new StepBuilder("serviceReaderStep", jobRepository)
                .<User, User>chunk(chunkSize, transactionManager)
                .reader(reader)
                .writer(userItemWriter)
                .build();
    }
}
