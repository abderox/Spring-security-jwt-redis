package com.expressionbesoins.restexpbesoin.dto;

/**
 * @autor abdelhadi mouzafir
 */


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



@Getter
@Setter
public class UserLoginDTO {

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String password;


    @NotNull
    @Email
    @NotEmpty
    private String email;

    // ? better to do it in frontend since this is a restful app
//    @AssertTrue
//    private Boolean terms;

}
