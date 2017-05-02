package com.xepsa.memo.controllers;

import com.xepsa.memo.client.UserServiceClient;
import com.xepsa.memo.model.User;
import com.xepsa.memo.services.DiscoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="v1/tools")
public class ServiceDiscoveryToolController {

    @Autowired
    private DiscoveryService discoveryService;

    @Autowired
    private UserServiceClient userService;

    @RequestMapping(value="/eureka/services",method = RequestMethod.GET)
    public List<String> getEurekaServices() {
        return discoveryService.getEurekaServices();
    }

    @RequestMapping(value="/test/user",  method = RequestMethod.GET)
    public String getUser() {
        User user = userService.getUser("fake_id");
        return user.getFullname();
    }


}
