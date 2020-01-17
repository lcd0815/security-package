package com.imooc.sucurity.core.validate.code.sms;

import org.springframework.stereotype.Component;

/**
 * lcd  2020/1/15
 * Description:
 */
@Component("smsSender")
public class SmsSenderImpl implements SmsSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机号:"+mobile+"发送验证码:"+code);
    }
}
