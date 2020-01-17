package com.imooc.sucurity.core.validate.code;

import com.imooc.sucurity.core.validate.code.bean.ValidateCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * lcd  2020/1/17
 * Description:
 */
@Component
public class ValidateCodeProcessorHolderImpl implements ValidateCodeProcessorHolder {
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessorMap;
    @Override
    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType validateCodeType) {
        return validateCodeProcessorMap.get(validateCodeType.name().toLowerCase()+"ValidateCodeProcessor");
    }
}
