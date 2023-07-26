package com.hemalpatel.wealthnavigator.domain;

public enum ERole {
    ROLE_USER,
    ROLE_ADMIN,
    INVALID;

    public static ERole fromString(String strRole) {
        for (ERole role : ERole.values()) {
            if (role.name().equalsIgnoreCase(strRole)) {
                return role;
            }
        }
        return ERole.INVALID;
    }
}
