package com.westlakefinancial.technology.enums;

import lombok.Getter;

/**
 * Result enumeration class
 *
 * @author jiapeng.wu
 */
@Getter
public enum ResultEnum {
    SUCCESS(101, "success"),
    FAILURE(102, "fail"),
    USER_NEED_AUTHORITIES(201, "User is not logged in"),
    USER_LOGIN_FAILED(202, "Incorrect user account or password"),
    USER_LOGIN_SUCCESS(203, "User logged in successfully"),
    USER_NO_ACCESS(204, "User does not have access"),
    USER_LOGOUT_SUCCESS(205, "User logged out successfully"),
    TOKEN_IS_BLACKLIST(206, "This token is a blacklist"),
    LOGIN_IS_OVERDUE(207, "Login has expired"),
    ;

    private final Integer code;

    private final String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public static ResultEnum parse(int code) {
        ResultEnum[] values = values();
        for (ResultEnum value : values) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new RuntimeException("Unknown code of ResultEnum");
    }
}
