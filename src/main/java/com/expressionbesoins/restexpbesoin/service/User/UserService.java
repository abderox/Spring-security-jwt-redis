package com.expressionbesoins.restexpbesoin.service.User;
/**
 * @autor abdelhadi mouzafir
 */

import com.expressionbesoins.restexpbesoin.dto.UserLoginDTO;
import com.expressionbesoins.restexpbesoin.exception.AlreadyUsedEmail;
import com.expressionbesoins.restexpbesoin.model.Privilege;
import com.expressionbesoins.restexpbesoin.model.Role;
import com.expressionbesoins.restexpbesoin.model.User;
import com.expressionbesoins.restexpbesoin.repository.RoleRepo;
import com.expressionbesoins.restexpbesoin.repository.UserRepo;
import com.expressionbesoins.restexpbesoin.service.SetRoleAndPrivilege;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class UserService implements IUserService {

    Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepo userRepo;
    // ? I use modelMapper to make object mapping easy , by automatically determining how on object model maps to another , based on conventions
    // ? instead of hardcoding the conversion using useless bunch of lines
    ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

   // @Autowired
    //private SessionRegistry sessionRegistry;

    // ! Depending on the needs we ll define some useful methods here
    // ! aftermaths

    // ! for testing  ************************
    @Override
    public User saveRegisteredUser(User user){
        User user_ = userRepo.findUserByUsername(user.getUsername());
        if(user_ == null) {
            user_ = userRepo.save(user);
        }
        return user_;
    }

    // * new account
    @Override
    public User registerNewUserAccount(final UserLoginDTO accountDto) {
        if (emailExists(accountDto.getEmail())) {
            throw new AlreadyUsedEmail("There is an account with that email address: " + accountDto.getEmail());
        }
        LOGGER.debug(accountDto.toString());
        final User user = this.convertToModel(accountDto);
        LOGGER.debug(user.toString());
        return userRepo.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }
    @Transactional
    public int deleteByuserName(String userName) {
        return userRepo.deleteUserByUsername(userName);
    }

    // ? model to dto conversion
    public UserLoginDTO convertToDTO(User user) {
        return modelMapper.map(user, UserLoginDTO.class);
    }

    // ? dto to model conversion
    public User convertToModel(UserLoginDTO userLoginDTO) {
        return modelMapper.map(userLoginDTO, User.class);
    }


    @Override
    public User getUser(String verificationToken) {
        return null;
    }



    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void createVerificationTokenForUser(User user, String token) {

    }
    private boolean emailExists(final String email) {
        return userRepo.findUserByEmail(email) != null;
    }
}
