package com.example.serviceReaderJob.reader;

import com.example.serviceReaderJob.domain.ResponseUser;
import com.example.serviceReaderJob.domain.User;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class UserReader implements ItemReader<User> {
    private RestTemplate restTemplate = new RestTemplate();
    private int page = 1;
    private List<User> users = new ArrayList<>();
    private int userIndex = 0;
    private int total = 0;

    @Value("${chunkSize}")
    private int chunkSize;
    @Value("${limit}")
    private int limit;
    @Value("${pageSize}")
    private int pageSize;

    @Override
    public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        User user;
        if(userIndex < users.size())
            user = users.get(userIndex);
        else
            user = null;
        userIndex++;
        return user;
    }

    @BeforeChunk
    private void beforeChunk(ChunkContext context) {
        for (int i = 0; i < chunkSize; i += pageSize) {
            if(total >= limit) return;
            users.addAll(this.fetchUserDataFromExternalApi());
            total += pageSize;
            page++;
        }
    }


    private List<User> fetchUserDataFromExternalApi() {
        var response = restTemplate
                .getForEntity(String.format("https://gorest.co.in/public/v1/users?page=%d", page),
                        ResponseUser.class);
        return response.getBody().getData();
    }

    @AfterChunk
    private void afterChunk(ChunkContext context) {
        userIndex = 0;
        users = new ArrayList<>();
    }
}
