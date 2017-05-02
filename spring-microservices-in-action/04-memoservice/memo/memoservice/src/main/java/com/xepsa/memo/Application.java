package com.xepsa.memo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping(value="v1/echo")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping(value="/{toEcho}",method = RequestMethod.GET)
    @RefreshScope   // enable config refresh enpoint: http://<yourserver>:<port>/refresh
    public String hello( @PathVariable("toEcho") String toEcho) {
        return String.format("{\"message\":\"%s\"}", toEcho);
    }
}
