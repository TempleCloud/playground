package com.xepsa.memo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RefreshScope
@EnableEurekaClient
@EnableDiscoveryClient // discovery client for interacting with service discovery server.
// @EnableFeignClients -- generate feign client for eureka service discovery endpoints.
@RestController
@RequestMapping(value="v1/echo")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value="/{toEcho}",method = RequestMethod.GET)
    @RefreshScope   // enable config refresh enpoint: http://<yourserver>:<port>/refresh
    public String hello( @PathVariable("toEcho") String toEcho) {
        return String.format("{\"message\":\"%s\"}", toEcho);
    }
}
