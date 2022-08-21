//package com.expressionbesoins.restexpbesoin.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import javax.servlet.http.HttpServletResponse;
//
//@EnableWebSecurity
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    CustomUserDetails userDetailsService;
//
//    @Autowired
//    PasswordEncoder bCryptPasswordEncoder;
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/user/registration").permitAll()
//                .anyRequest().authenticated()
//                .and();
//
//        http.addFilter(new JWTAuthenticationFilter(authenticationManager()))
//                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
//                .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE))
//          ;
//        ;
//
//
//
//    }
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(bCryptPasswordEncoder);
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean( );
//    }
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(
//                "/v2/api-docs/**",
//                            "/configuration/ui",
//                            "/swagger-resources/**",
//                            "/configuration/security",
//                            "/swagger-ui.html",
//                            "/swagger-ui/**",
//                            "/v3/api-docs/**",
//                            "/webjars/**",
//                            "/user/registration");
//    }
//
////    @Bean
////    CorsConfigurationSource corsConfigurationSource() {
////        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////
////        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
////        source.registerCorsConfiguration("/**", corsConfiguration);
////
////
////        return source;
////    }
//
//
//
//}