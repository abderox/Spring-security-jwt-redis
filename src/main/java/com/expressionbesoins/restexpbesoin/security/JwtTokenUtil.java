package com.expressionbesoins.restexpbesoin.security;


import com.expressionbesoins.restexpbesoin.model.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    private int EXPIRE_TOKEN_TIME = 3600;
    @Value("${app.jwt.secret}")
    private String SECRET_KEY ;
    private String TOKEN_PREFIX = "Bearer ";
    private String HEADER_STRING = "Authorization";

    Logger logger = LoggerFactory.getLogger(getClass());

    public String genAccessToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId()+","+user.getEmail())
                .claim("roles", user.getRoles())
                .setIssuer("abdelhadi")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TOKEN_TIME * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token) {
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
        return false;
    }

    public String getSubject(String token)
    {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject();
    }
    
    
    public Claims parseClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }








}
