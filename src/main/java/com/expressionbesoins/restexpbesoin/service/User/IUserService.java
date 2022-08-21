package com.expressionbesoins.restexpbesoin.service.User;

import com.expressionbesoins.restexpbesoin.dto.UserLoginDTO;
import com.expressionbesoins.restexpbesoin.dto.UserRegDTO;
import com.expressionbesoins.restexpbesoin.model.User;

import java.util.List;

public interface IUserService {

    User registerNewUserAccount(User user);

    User getUser(String verificationToken);

    User saveRegisteredUser(User user);

    void deleteUser(User user);

    void createVerificationTokenForUser(User user, String token);
     User findUserByUsername(String username);

    User findAndLogin(UserRegDTO accountDto);

    List<User> findAll();
}
