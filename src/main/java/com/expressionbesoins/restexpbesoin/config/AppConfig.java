package com.expressionbesoins.restexpbesoin.config;

/**
 * @autor abdelhadi mouzafir
 */

import org.modelmapper.ModelMapper;
import org.springframework.aop.scope.DefaultScopedObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ! PLEASE PAY ATTENTION ON THESE ANNOTATIONS NEXT TIME

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
