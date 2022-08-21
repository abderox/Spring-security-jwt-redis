package com.expressionbesoins.restexpbesoin.util.repo;

import com.expressionbesoins.restexpbesoin.util.JwtToken;
import org.springframework.stereotype.Repository;


public interface RedisRepository {

    void add(JwtToken object);

    void delete(String username);

    JwtToken findToken(String username);


}
