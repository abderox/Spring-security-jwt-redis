package com.expressionbesoins.restexpbesoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = { "com.expressionbesoins.restexpbesoin"})
public class RestExpBesoinApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestExpBesoinApplication.class, args);
    }

}
