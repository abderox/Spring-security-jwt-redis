package com.expressionbesoins.restexpbesoin.config;

/**
 * @autor abdelhadi mouzafir
 */

import com.expressionbesoins.restexpbesoin.util.MessagePublisher;
import com.expressionbesoins.restexpbesoin.util.MessagePublisherImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// ! PLEASE PAY ATTENTION ON THESE ANNOTATIONS NEXT TIME

@Configuration
@ComponentScan({ "com.expressionbesoins.restexpbesoin" })
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }





}
