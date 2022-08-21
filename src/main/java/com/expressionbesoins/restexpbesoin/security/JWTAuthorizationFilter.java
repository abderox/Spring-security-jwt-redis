//package com.expressionbesoins.restexpbesoin.security;
//
//import com.expressionbesoins.restexpbesoin.model.Role;
//import com.expressionbesoins.restexpbesoin.model.User;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//
//public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
//
//
//    private String SECRET_KEY = "abdelhadi@spontaneous.com";
//    private String TOKEN_PREFIX = "Bearer ";
//    private String HEADER_STRING = "Authorization";
//
//    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//        String header = request.getHeader(HEADER_STRING);
//
//        if ( header == null || !header.startsWith(TOKEN_PREFIX) ) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            chain.doFilter(request, response);
//            return;
//        }
//
//        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
//        System.out.println(" working");
//        SecurityContextHolder.getContext( ).setAuthentication(authentication);
//
//        System.out.println("Authenticated " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated() );
//        System.out.println("also working");
//
//    }
//
//    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
//
//        String token = genAccessToken(request);
//        User userEntity = new User( );
//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>( );
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//
//        if ( token != null ) {
//            Claims claims = parseClaims(token);
//            // parse the token.
//            String user = getSubject(token).split(",")[1];
//            userEntity.setEmail(user);
//            userEntity.setId(Long.parseLong(getSubject(token).split(",")[0]));
//            Set<Role> roles = new HashSet<Role>( );
////            roles.add(new Role(claims.get("role", String.class)));
//
//            UserDetailsImpl userDetails = new UserDetailsImpl(userEntity, authorities);
//            logger.fatal("user : " + user);
//
//            if ( user != null ) {
//                // new arraylist means authorities
//                System.out.println("ID /" + userDetails.getUser( ).getId( ));
//                return new UsernamePasswordAuthenticationToken(userDetails, token, authorities);
//            }
//            System.out.println("not working");
//
//            return null;
//        }
//
//        return null;
//    }
//
//    private Claims parseClaims(String token) {
//        return Jwts.parser( ).setSigningKey(SECRET_KEY).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody( );
//    }
//
////    public UserDetailsImpl getUserDetails(String token) {
////        User user = new User( );
////        Claims claims = jwtTokenUtil.parseClaims(token);
////        String[] splitSuubject = jwtTokenUtil.getSubject(token).split(",");
////        user.setId(Long.parseLong(splitSuubject[0]));
////        user.setEmail(splitSuubject[1]);
////        Set<Role> roles = (Set<Role>) claims.get("roles");
////
////        return new UserDetailsImpl(user, new HashSet<>( ));
////    }
//
//    public String getSubject(String token) {
//        return Jwts.parser( ).setSigningKey(SECRET_KEY).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody( ).getSubject( );
//    }
//
//    public boolean hasAuthorizationHeader(HttpServletRequest request) {
//        // TODO Auto-generated method stub
//        String header = request.getHeader("Authorization");
//        System.out.println("header : " + header);
//        return header != null && header.startsWith("Bearer");
//    }
//
//    public String genAccessToken(HttpServletRequest request) {
//        String header = request.getHeader("Authorization");
//        String token = header.split(" ")[1].trim( );
//        return token;
//    }
//}
