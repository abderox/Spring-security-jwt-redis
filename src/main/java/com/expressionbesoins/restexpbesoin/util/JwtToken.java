package com.expressionbesoins.restexpbesoin.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor @NoArgsConstructor
public class JwtToken {

    private String token;
    private String username;


}
