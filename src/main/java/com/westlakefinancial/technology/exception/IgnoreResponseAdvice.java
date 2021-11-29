package com.westlakefinancial.technology.exception;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @InterfaceName IgnoreResponseAdvice
 * @Description TODO
 * @Author james
 * @Date 2021/11/29 9:15
 * @Version 1.0
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResponseAdvice {

    /**
     * 是否进行全局异常处理封装
     * @return true:进行处理;  false:不进行异常处理
     */
    boolean errorDispose() default true;

}
