package com.imooc.sucurity.core.validate.code.sms;

/**
 * lcd  2020/1/15
 * Description:
 */
public interface SmsSender {
    void send(String mobile, String code);
}
