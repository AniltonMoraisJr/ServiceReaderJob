package com.example.serviceReaderJob.writer;

import com.example.serviceReaderJob.domain.User;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserWriterConfig {

    @Bean
    public ItemWriter<User> userItemWriter() {
        return items -> items.forEach(System.out::println);
    }
}
