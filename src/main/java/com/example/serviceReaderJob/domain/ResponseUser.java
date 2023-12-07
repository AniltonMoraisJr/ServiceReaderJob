package com.example.serviceReaderJob.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResponseUser {
    private List<User> data;
}
