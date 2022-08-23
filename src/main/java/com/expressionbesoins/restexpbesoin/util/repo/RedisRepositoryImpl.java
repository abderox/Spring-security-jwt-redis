package com.expressionbesoins.restexpbesoin.util.repo;

import com.expressionbesoins.restexpbesoin.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class RedisRepositoryImpl implements RedisRepository {

    private static final String KEY = "JwtToken";
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;

    }

    // ! I love this annotation
    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void add(JwtToken object) {
        hashOperations.put(KEY,object.getUsername(),object.getToken());
    }

    @Override
    public void delete(String username) {
        hashOperations.delete(KEY,username);
    }

    @Override
    public JwtToken findToken(String username) {
        return new JwtToken( (String) hashOperations.get(KEY,username),username);
    }

    @Override
    public boolean isFoundToken(String username) {
        return findToken(username) != null;
    }



}
