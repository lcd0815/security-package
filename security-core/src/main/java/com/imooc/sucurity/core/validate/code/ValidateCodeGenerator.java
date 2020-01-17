package com.imooc.sucurity.core.validate.code;

import javax.servlet.http.HttpServletRequest;

/**
 * lcd  2020/1/9
 * Description:
 */
public interface ValidateCodeGenerator {
    ValidateCode generate(HttpServletRequest request);
}
