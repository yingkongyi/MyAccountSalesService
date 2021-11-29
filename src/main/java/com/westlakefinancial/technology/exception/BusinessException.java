package com.westlakefinancial.technology.exception;

import com.westlakefinancial.technology.enums.BusinessErrorCode;
import com.westlakefinancial.technology.enums.CommonErrorCode;

/**
 * @ClassName BusinessException
 * @Description TODO
 * @Author james
 * @Date 2021/11/29 9:14
 * @Version 1.0
 **/

public class BusinessException extends RuntimeException {

    private String code;
    private boolean isShowMsg = true;

    public String getCode() {
        return code;
    }

    public boolean isShowMsg() {
        return isShowMsg;
    }

    /**
     * 使用枚举传参
     *
     * @param errorCode 异常枚举
     */
    public BusinessException(BusinessErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    /**
     * 使用CommonErrorCode枚举传参
     *
     * @param errorCode 异常枚举
     */
    public BusinessException(CommonErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    /**
     * 使用自定义消息
     *
     * @param code 值
     * @param msg  详情
     */
    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
    }

}