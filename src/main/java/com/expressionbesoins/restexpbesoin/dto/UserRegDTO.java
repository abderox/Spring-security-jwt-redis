package com.expressionbesoins.restexpbesoin.dto;

/**
 * @autor abdelhadi mouzafir
 */


import lombok.Data;


@Data
public class UserRegDTO {



    private String passwordUser;
    private String emailUser;





// ? better to do it in frontend since this is a restful app
//    @AssertTrue
//    private Boolean terms;

}
