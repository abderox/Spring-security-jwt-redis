package com.expressionbesoins.restexpbesoin.security;


import com.expressionbesoins.restexpbesoin.exception.ApiError;
import com.expressionbesoins.restexpbesoin.model.Role;
import com.expressionbesoins.restexpbesoin.model.User;
import com.expressionbesoins.restexpbesoin.util.repo.RedisRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtTokenUtil {

    private int EXPIRE_TOKEN_TIME = 3600;
    @Value("${app.jwt.secret}")
    private String SECRET_KEY ;
    private String TOKEN_PREFIX = "Bearer ";
    private String HEADER_STRING = "Authorization";
    @Autowired
    RedisRepository redisRepository;

    Logger logger = LoggerFactory.getLogger(getClass());

    public String genAccessToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId()+","+user.getEmail())
                .claim("roles", user.getRoles().toString())
                .setIssuer("abdelhadi")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TOKEN_TIME * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token )  {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token.replace(TOKEN_PREFIX, ""));
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("ExpiredJwtException: {}", e.getMessage());

        }catch (IllegalArgumentException e) {
            logger.error("Exception: {}", e.getMessage());
        }
        catch (MalformedJwtException e) {
            logger.error("MalformedJwtException: {}", e.getMessage());
        }catch (UnsupportedJwtException e) {
            logger.error("UnsupportedJwtException: {}", e.getMessage());
        }catch (SignatureException e) {
            logger.error("SignatureException: {}", e.getMessage());
        }
        redisRepository.delete(token);
        return false;
    }

    public String getSubject(String token)
    {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject();
    }
    
    
    public Claims parseClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
    }


    public Set<String> getAuthorities(String token) throws IOException {
        Claims claims = parseClaims(token);
        String auth = (String) claims.get("roles");
        System.out.println("auth : "+auth);
        auth = auth.replace("[", "").replace("]", "");
        List<String> auth__ = Arrays.asList( auth.split(","));
        auth__=auth__.stream().filter(role -> role.contains("name") && role.contains("ROLE")).map(role -> role.replace("name=", "").replace(")","")).collect(Collectors.toList());
        return new HashSet<>(auth__);
    }






}
