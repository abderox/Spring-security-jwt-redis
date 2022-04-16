package com.expressionbesoins.restexpbesoin.service;
/**
 * @autor abdelhadi mouzafir
 */

import com.expressionbesoins.restexpbesoin.model.User;
import com.expressionbesoins.restexpbesoin.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public User findUserByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }
    @Transactional
    public int deleteByuserName(String userName) {
        return userRepo.deleteUserByUsername(userName);
    }
}
