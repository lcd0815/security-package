package com.imooc.sucurity.core.validate.code.impl;

import com.imooc.sucurity.core.constant.SecurityConstant;
import com.imooc.sucurity.core.validate.code.ValidateCode;
import com.imooc.sucurity.core.validate.code.ValidateCodeException;
import com.imooc.sucurity.core.validate.code.ValidateCodeGenerator;
import com.imooc.sucurity.core.validate.code.ValidateCodeProcessor;
import com.imooc.sucurity.core.validate.code.bean.ValidateCodeType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.LocalDateTime;
import java.util.Map;


/**
 * lcd  2020/1/15
 * Description:
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    /**
     * spring会收集所有{@link ValidateCodeGenerator}接口的实现
     **/
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGeneratorMap;

    @Override
    public void create(ServletWebRequest request) {
        //主干逻辑
        C code = generate(request);
        save(code, request);
        send(code, request);
    }


    protected C generate(ServletWebRequest request) {
        C code = (C) validateCodeGeneratorMap.get(getProcessorType(request)+"CodeGenerator").generate(request.getRequest());
        return code;
    }

    private void save(ValidateCode code, ServletWebRequest request) {
        sessionStrategy.setAttribute(request, SecurityConstant.SESSION_KEY_PREFIX+getProcessorType(request).toUpperCase(), code);
    }


    public abstract void send(ValidateCode code, ServletWebRequest request);

    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
    }

    @Override
    public void validate(ServletWebRequest request,ValidateCodeType type) {
        ValidateCode sessionCode = (ValidateCode) sessionStrategy.getAttribute(request, SecurityConstant.SESSION_KEY_PREFIX +type.name());
        String requestCode = null;
        try {
            requestCode = ServletRequestUtils.getStringParameter(request.getRequest(), type.name().toLowerCase()+"Code");
        } catch (ServletRequestBindingException e) {
            throw new RuntimeException("参数错误");
        }
        if (StringUtils.isBlank(requestCode)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }
        if (sessionCode == null) {
            throw new ValidateCodeException("验证码不存在");
        }
        if (sessionCode.isExpire()) {
            sessionStrategy.removeAttribute(request, SecurityConstant.SESSION_KEY_PREFIX +type.name().toUpperCase());
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(requestCode, sessionCode.getCode())) {
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(request, SecurityConstant.SESSION_KEY_PREFIX +type.name().toUpperCase());
    }
}
