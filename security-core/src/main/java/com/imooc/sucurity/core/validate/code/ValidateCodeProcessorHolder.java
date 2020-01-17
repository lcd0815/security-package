package com.imooc.sucurity.core.validate.code;

import com.imooc.sucurity.core.validate.code.bean.ValidateCodeType;

/**
 * lcd  2020/1/17
 * Description:
 */
public interface ValidateCodeProcessorHolder {
    ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType validateCodeType);
}
