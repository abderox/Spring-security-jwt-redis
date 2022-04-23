package com.expressionbesoins.restexpbesoin.controller;

import com.expressionbesoins.restexpbesoin.dto.UserLoginDTO;
import com.expressionbesoins.restexpbesoin.model.User;
import com.expressionbesoins.restexpbesoin.service.User.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;;



@RestController
public class RegistraionController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private IUserService userService;

    @Autowired
    private MessageSource messages;


    @Autowired
    private Environment env;



    // ? Registration
    @PostMapping("/user/registration")
    public ResponseEntity<User> registerUserAccount(@RequestBody UserLoginDTO accountDto) {
        LOGGER.debug("Registering user account with information: {}", accountDto);
        return new ResponseEntity<>(userService.registerNewUserAccount(accountDto),HttpStatus.OK);
    }

    @GetMapping("/test")
    public String hello()
    {
        return "<h1>Expression de besoins</h1>";
    }

}
