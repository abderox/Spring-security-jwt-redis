package com.expressionbesoins.restexpbesoin.enums;


public enum RoleEnum {


    ROLE_USER(RolesNames.ROLE_USER),
    ROLE_ADMIN(RolesNames.ROLE_ADMIN),
    ROLE_SUPER_ADMIN(RolesNames.ROLE_SUPER_ADMIN),
    ;

    private final String label;

    // ? I am using this class since I am working on a clean code ;
    // ? we can do it the regular way

    public class RolesNames {

        public static final String ROLE_USER = "ROLE_USER";
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
        public static final String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";

    }


    private RoleEnum(String label) {
        this.label = label;
    }

    public String toString() {
        return this.label;
    }

}
