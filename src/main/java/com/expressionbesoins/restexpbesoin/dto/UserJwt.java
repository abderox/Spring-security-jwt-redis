package com.expressionbesoins.restexpbesoin.dto;

/**
 * @autor abdelhadi mouzafir
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserJwt {



    private String emailUser;
    private String accessToken;





// ? better to do it in frontend since this is a restful app
//    @AssertTrue
//    private Boolean terms;

}
