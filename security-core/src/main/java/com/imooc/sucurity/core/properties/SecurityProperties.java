package com.imooc.sucurity.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.Valid;

/**
 * lcd  2020/1/6
 * Description:
 */
@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();


    private ValidateCodeProperties code = new ValidateCodeProperties();

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
