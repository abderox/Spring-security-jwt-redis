package com.expressionbesoins.restexpbesoin.controller;

import com.expressionbesoins.restexpbesoin.dto.UserJwt;
import com.expressionbesoins.restexpbesoin.dto.UserLoginDTO;
import com.expressionbesoins.restexpbesoin.dto.UserRegDTO;
import com.expressionbesoins.restexpbesoin.model.Role;
import com.expressionbesoins.restexpbesoin.model.User;
import com.expressionbesoins.restexpbesoin.model.enums.PrivilegeEnum;
import com.expressionbesoins.restexpbesoin.security.JwtTokenUtil;
import com.expressionbesoins.restexpbesoin.security.UserDetailsImpl;
import com.expressionbesoins.restexpbesoin.service.RoleService;
import com.expressionbesoins.restexpbesoin.service.User.IUserService;
import com.expressionbesoins.restexpbesoin.util.JwtToken;
import com.expressionbesoins.restexpbesoin.util.repo.RedisRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
;
import javax.annotation.security.RolesAllowed;
import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/api/v1")
public class RegistraionController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass( ));

    @Autowired
    private IUserService userService;

    @Autowired
    private MessageSource messages;

    @Autowired
    RoleService roleService;

    @Autowired
    private Environment env;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    RedisRepository redisRepository;

    // ? Registration
    @PostMapping("/auth/user/registration")
    public User registerUserAccount(@RequestBody UserLoginDTO accountDto) {
        LOGGER.info("Registering user account with information: {}", accountDto);
        Role adminRole = roleService.findByName(PrivilegeEnum.ROLE_ADMIN);
        Role userRole = roleService.findByName(PrivilegeEnum.ROLE_USER);
        Set<Role> roles = new HashSet<>( );
        roles.add(adminRole);
        roles.add(userRole);
        User user = convertToModel(accountDto);
        LOGGER.info("Registering user account with information: {}", user);
        user.setPassword(User.encodePassword(user.getPassword( )));
        user.setRoles(roles);
        return userService.registerNewUserAccount(user);
    }

    private User convertToModel(UserLoginDTO userLoginDTO) {
//        modelMapper.getConfiguration()
//                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return modelMapper.map(userLoginDTO, User.class);
    }

    private User convertToModelLogin(UserRegDTO userLoginDTO) {
//        modelMapper.getConfiguration()
//                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return modelMapper.map(userLoginDTO, User.class);
    }

    //    @PreAuthorize("hasAuthority('ROLE_USER')")

    @RolesAllowed(PrivilegeEnum.PrivilegeNames.ROLE_SUPER_ADMIN)
    @GetMapping("/test")
    public String hello() {
        return "<h1>Expression de besoins</h1>";
    }


    @GetMapping("/users") // ? get user profile
    public ResponseEntity<?> getUserProfile() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String credentials = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        boolean isFoundToken =redisRepository.isFoundToken(userDetails.getUsername());
        String token = redisRepository.findToken(userDetails.getUsername()).getToken();

        if( isFoundToken && token.equals(credentials) ) {
            System.out.println(redisRepository.findToken(userDetails.getUsername()).getToken());
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        redisRepository.delete(userDetails.getUsername());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody UserRegDTO accountDto) {

        try {
            UserJwt userJwt = null;
            UserDetailsImpl myUserDetails = null;
//            if ( SecurityContextHolder.getContext( ).getAuthentication( ).isAuthenticated( ) ) {
//                System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() );
//                 myUserDetails = (UserDetailsImpl) SecurityContextHolder.getContext( ).getAuthentication( ).getPrincipal( );
//                 userJwt = new UserJwt(myUserDetails.getUsername( ), SecurityContextHolder.getContext( ).getAuthentication( ).getCredentials( ).toString( ));
//                return new ResponseEntity<>(userJwt, HttpStatus.OK);
//            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            accountDto.getEmailUser( ),
                            accountDto.getPasswordUser( )
                    )

            );
            // ! do not do it the old way SpringSecurityContextHolder.getContext().setAuthentication(authentication); throws an exception of casting
            myUserDetails = (UserDetailsImpl) authentication.getPrincipal( );
            String accessToken = jwtTokenUtil.genAccessToken(myUserDetails.getUser( ));
            userJwt = new UserJwt(myUserDetails.getUsername( ), accessToken);
            redisRepository.add(new JwtToken( accessToken,myUserDetails.getUsername( )));
            return new ResponseEntity<>(userJwt, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(accountDto, HttpStatus.UNAUTHORIZED);
        }

    }
}
