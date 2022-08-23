package com.expressionbesoins.restexpbesoin.security;


import javax.servlet.http.HttpServletResponse;

import com.expressionbesoins.restexpbesoin.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(
        prePostEnabled = false, securedEnabled = false, jsr250Enabled = true
)
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    CustomUserDetails userDetailsService;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf( ).disable( );
        http.sessionManagement( ).sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests( )
                .antMatchers("/api/v1/auth/**").permitAll( )
                .antMatchers(HttpMethod.POST,"/api/default/**").permitAll( )
                .antMatchers(
                        "/v2/api-docs/**",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/webjars/**",
                        "/user/registration",
                        "/api/v1/logout").permitAll()
                .anyRequest( ).authenticated( );

//        http.exceptionHandling( )
//                .authenticationEntryPoint(
//                        (request, response, ex) -> {
//                            response.sendError(
//                                    HttpServletResponse.SC_UNAUTHORIZED,
//                                    "Invalid token"
//                            );
//                        }
//                );
        http.exceptionHandling( )
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    "Invalid token"
                            );
                        }
                );

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilter( new JWTAuthenticationFilter( authenticationManager() ) );
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean( );
    }
}
