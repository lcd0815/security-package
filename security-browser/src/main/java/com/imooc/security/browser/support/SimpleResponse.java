package com.imooc.security.browser.support;

/**
 * lcd  2020/1/6
 * Description:
 */
public class SimpleResponse {
    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public SimpleResponse(Object content) {
        this.content = content;
    }
}
