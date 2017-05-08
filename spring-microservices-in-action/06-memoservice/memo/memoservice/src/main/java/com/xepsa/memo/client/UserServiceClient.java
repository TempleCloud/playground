package com.xepsa.memo.client;

import com.xepsa.memo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserServiceClient {

    @Autowired
    RestTemplate restTemplate;

    public User getUser(String userId){
        ResponseEntity<User> restExchange =
                restTemplate.exchange(
                        "http://userservice/v1/user/{userId}",
                        HttpMethod.GET,
                        null,
                        User.class,
                        userId);

        return restExchange.getBody();
    }
}