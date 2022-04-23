package com.expressionbesoins.restexpbesoin.service.User;

import com.expressionbesoins.restexpbesoin.dto.UserLoginDTO;
import com.expressionbesoins.restexpbesoin.model.User;

public interface IUserService {

    User registerNewUserAccount(UserLoginDTO accountDto);

    User getUser(String verificationToken);

    User saveRegisteredUser(User user);

    void deleteUser(User user);

    void createVerificationTokenForUser(User user, String token);
}
