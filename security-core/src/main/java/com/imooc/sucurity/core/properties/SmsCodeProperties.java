package com.imooc.sucurity.core.properties;

/**
 * lcd  2020/1/15
 * Description:
 */
public class SmsCodeProperties {
    private int length = 6;
    private int expireIn = 60;
    private String url;//用来存储需要 被验证码过滤器拦截需要拦截的url,多个的话用逗号分隔

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
