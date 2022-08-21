package com.expressionbesoins.restexpbesoin.security;

import com.expressionbesoins.restexpbesoin.model.Role;
import com.expressionbesoins.restexpbesoin.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@Component
public class JwtTokenFilter  extends OncePerRequestFilter {

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // TODO Auto-generated method stub

            if (!hasAuthorizationBearer(request)) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = getAccessToken(request);

            if (!jwtTokenUtil.validateToken(token)) {
                filterChain.doFilter(request, response);
                return;
            }

            setAuthenticationCoontext( request,token);
            filterChain.doFilter(request, response);

    }

    private void setAuthenticationCoontext(HttpServletRequest request, String token) {
        UserDetailsImpl userDetails = getUserDetails(token);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, token,null);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        System.out.println("authentication : "+authentication.getPrincipal().toString());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UserDetailsImpl getUserDetails(String token) {
        User user = new User( );
//        Claims claims = jwtTokenUtil.parseClaims(token);
        String[] splitSuubject = jwtTokenUtil.getSubject(token).split(",");
        user.setId(Long.parseLong(splitSuubject[0]));
        user.setEmail(splitSuubject[1]);

        return new UserDetailsImpl(user, new HashSet<>( ));
    }

    private boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if ( ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
            return false;
        }
        return true;
    }

    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();
        return token;
    }

}
