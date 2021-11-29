package com.westlakefinancial.technology.enums;

public enum BusinessErrorCode {

    /**
     * 通用业务异常
     */
    BUSINESS_ERROR("CLOUD800", "业务异常");

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    BusinessErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}

