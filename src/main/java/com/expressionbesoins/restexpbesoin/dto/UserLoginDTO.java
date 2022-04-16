package com.expressionbesoins.restexpbesoin.dto;

import com.expressionbesoins.restexpbesoin.Constraints.FieldMatch;
import lombok.Data;
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
    @NotEmpty
    private String confirmPassword;

    @NotNull
    @Email
    @NotEmpty
    private String email;

    @NotNull
    @Email
    @NotEmpty
    private String confirmEmail;

    @AssertTrue
    private Boolean terms;

}
