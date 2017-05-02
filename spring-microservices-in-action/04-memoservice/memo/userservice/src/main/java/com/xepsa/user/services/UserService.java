package com.xepsa.user.services;

import com.xepsa.user.config.ServiceConfig;
import com.xepsa.user.model.User;
import com.xepsa.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RefreshScope
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ServiceConfig config;


    public User getUser(String userId){
        User user = userRepository.findById(userId);
        return user;
    }

    public void saveUser(User user) {
        user.withId(UUID.randomUUID().toString());
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user.getId());
    }

}
