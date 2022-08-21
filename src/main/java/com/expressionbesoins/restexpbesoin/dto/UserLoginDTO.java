package com.expressionbesoins.restexpbesoin.dto;

/**
 * @autor abdelhadi mouzafir
 */


import com.expressionbesoins.restexpbesoin.model.Role;
import com.expressionbesoins.restexpbesoin.model.enums.PrivilegeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Data
public class UserLoginDTO {

    private String firstNameUser;


    private String lastNameUser;


    private String passwordUser;


    private String emailUser;


    private boolean enabledUser;


    private String usernameUser;





// ? better to do it in frontend since this is a restful app
//    @AssertTrue
//    private Boolean terms;

}
