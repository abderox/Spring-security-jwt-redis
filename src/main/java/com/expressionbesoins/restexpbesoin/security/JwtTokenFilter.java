package com.expressionbesoins.restexpbesoin.security;

import com.expressionbesoins.restexpbesoin.exception.ApiError;
import com.expressionbesoins.restexpbesoin.model.Role;
import com.expressionbesoins.restexpbesoin.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
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
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // TODO Auto-generated method stub

        if ( !hasAuthorizationBearer(request) ) {
            filterChain.doFilter(request, response);
            return;
        }


//        if ( SecurityContextHolder.getContext( ).getAuthentication( ) == null ) {
//
//            ApiError errorResponse = new ApiError(HttpStatus.UNAUTHORIZED, "Unauthorized : invalid/expired token");
//            response.setStatus(HttpStatus.UNAUTHORIZED.value( ));
//            response.getWriter( ).write(errorResponse.convertToJson(errorResponse));
//            return;
//        }

        String token = getAccessToken(request);
        if ( !jwtTokenUtil.validateToken(token) ) {
            filterChain.doFilter(request, response);
            return;
        }

        setAuthenticationCoontext(request, token);
        filterChain.doFilter(request, response);

    }

    private void setAuthenticationCoontext(HttpServletRequest request, String token) throws IOException {
        UserDetailsImpl userDetails = getUserDetails(token);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities( ));
        authentication.setDetails(new WebAuthenticationDetailsSource( ).buildDetails(request));
        System.out.println("authentication : " + authentication.getPrincipal( ).toString( ));
        SecurityContextHolder.getContext( ).setAuthentication(authentication);
    }

    private UserDetailsImpl getUserDetails(String token) throws IOException {
        User user = new User( );
        String[] splitSuubject = jwtTokenUtil.getSubject(token).split(",");
        user.setId(Long.parseLong(splitSuubject[0]));
        user.setEmail(splitSuubject[1]);
        Set<String> roles = jwtTokenUtil.getAuthorities(token);
        return new UserDetailsImpl(user, new ArrayList<>( ), roles);
    }

    private boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if ( ObjectUtils.isEmpty(header) || !header.startsWith("Bearer") ) {
            return false;
        }
        return true;
    }

    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim( );
        return token;
    }


}
