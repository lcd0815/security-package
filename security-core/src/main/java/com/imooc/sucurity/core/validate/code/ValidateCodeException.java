package com.imooc.sucurity.core.validate.code;


import org.springframework.security.core.AuthenticationException;

/**
 * lcd  2020/1/8
 * Description:
 * 父类:认证相关的异常
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
