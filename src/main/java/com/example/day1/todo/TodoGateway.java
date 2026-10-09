package com.example.day1.todo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class TodoGateway {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${base_url}")
    private String baseUrl;

    public Optional<TodoResponse> getById(int id) {
        String url = baseUrl + "/todos/" + id;
        try {
            return Optional.ofNullable(restTemplate.getForObject(url, TodoResponse.class));
        }catch(RuntimeException e) {
            throw new RuntimeException("Todo API error with status 500");
        }
    }

}
