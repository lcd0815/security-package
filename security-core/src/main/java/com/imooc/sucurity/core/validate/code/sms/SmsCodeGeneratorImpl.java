package com.imooc.sucurity.core.validate.code.sms;

import com.imooc.sucurity.core.properties.SecurityProperties;
import com.imooc.sucurity.core.validate.code.ValidateCode;
import com.imooc.sucurity.core.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * lcd  2020/1/15
 * Description:
 */
@Component("smsCodeGenerator")
public class SmsCodeGeneratorImpl implements ValidateCodeGenerator {
    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public ValidateCode generate(HttpServletRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code,securityProperties.getCode().getSms().getExpireIn());
    }
}
