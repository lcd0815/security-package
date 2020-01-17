package com.imooc.sucurity.core.validate.code.sms;

import com.imooc.sucurity.core.validate.code.ValidateCode;
import com.imooc.sucurity.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * lcd  2020/1/15
 * Description:
 */
@Component
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor {

    @Autowired
    private SmsSender smsSender;

    @Override
    public void send(ValidateCode code, ServletWebRequest request) {
        String mobile = null;
        try {
            mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
        } catch (ServletRequestBindingException e) {
            throw new RuntimeException("缺少手机号参数");
        }
        smsSender.send(mobile, code.getCode());
    }



}
