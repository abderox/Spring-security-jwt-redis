package com.expressionbesoins.restexpbesoin.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RestExpBesoinApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestExpBesoinApplication.class, args);
    }
    @GetMapping("/test")
    public String hello()
    {
        return "<h1>Expression de besoins</h1>";
    }
}
