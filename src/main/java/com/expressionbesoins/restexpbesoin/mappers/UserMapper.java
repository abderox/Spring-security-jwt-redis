package com.expressionbesoins.restexpbesoin.mappers;

/**
 * @autor abdelhadi mouzafir
 */

import com.expressionbesoins.restexpbesoin.dto.UserLoginDTO;
import com.expressionbesoins.restexpbesoin.model.User;
import org.modelmapper.ModelMapper;


public class UserMapper {


    // ? I use modelMapper to make object mapping easy , by automatically determining how on object model maps to another , based on conventions


    ModelMapper modelMapper;

    public UserLoginDTO convertToDTO(User user) {
        return modelMapper.map(user, UserLoginDTO.class);
    }

    public User convertToModel(UserLoginDTO userLoginDTO) {
        return modelMapper.map(userLoginDTO, User.class);
    }

}
