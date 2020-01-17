package com.imooc.sucurity.core.validate.code;

import com.imooc.sucurity.core.validate.code.bean.ValidateCodeType;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * lcd  2020/1/15
 * Description:
 */
public interface ValidateCodeProcessor {

    void create(ServletWebRequest request);

    void validate(ServletWebRequest request, ValidateCodeType validateCodeType);
}
