package com.expressionbesoins.restexpbesoin.util.repo;

import com.expressionbesoins.restexpbesoin.util.JwtToken;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface RedisRepository {

    void add(JwtToken object);

    void delete(String username);

    JwtToken findToken(String username);

    boolean isFoundToken(String username);




}
