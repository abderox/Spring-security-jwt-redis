package com.expressionbesoins.restexpbesoin.service.User;
/**
 * @autor abdelhadi mouzafir
 */

import com.expressionbesoins.restexpbesoin.dto.UserLoginDTO;
import com.expressionbesoins.restexpbesoin.model.Privilege;
import com.expressionbesoins.restexpbesoin.model.Role;
import com.expressionbesoins.restexpbesoin.model.User;
import com.expressionbesoins.restexpbesoin.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    // ? I use modelMapper to make object mapping easy , by automatically determining how on object model maps to another , based on conventions
    // ? instead of hardcoding the conversion using useless bunch of lines
    ModelMapper modelMapper;

    // ! Depending on the needs we ll define some useful methods here
    // ! aftermaths

    public User saveUser(User user){
        user = userRepo.findUserByUsername(user.getUsername());
        if(user == null) {
            userRepo.save(user);
        }
        return user;
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
}
