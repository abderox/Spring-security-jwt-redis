package com.expressionbesoins.restexpbesoin.controller;

/**
 * @autor abdelhadi mouzafir
 */

import com.expressionbesoins.restexpbesoin.model.User;
import com.expressionbesoins.restexpbesoin.service.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping("/api/user/profile/please") // ? get user profile
    public List<User> getUserProfile() {
        System.out.println("getUserProfile" );
        System.out.println(userService.findAll().size());
        return userService.findAll();
    }

}
