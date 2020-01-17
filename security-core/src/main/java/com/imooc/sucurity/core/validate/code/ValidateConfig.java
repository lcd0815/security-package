package com.imooc.sucurity.core.validate.code;

import com.imooc.sucurity.core.validate.code.image.ImageCodeGeneratorImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * lcd  2020/1/9
 * Description:
 */
@Configuration
public class ValidateConfig {
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        return new ImageCodeGeneratorImpl();
    }

}
