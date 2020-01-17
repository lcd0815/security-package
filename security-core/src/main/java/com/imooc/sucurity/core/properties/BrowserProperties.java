package com.imooc.sucurity.core.properties;

/**
 * lcd  2020/1/6
 * Description:
 */
public class BrowserProperties {
    private String loginPage="/imooc-signIn.html";
    private LoginType loginType = LoginType.JSON;
    private int rememberMeTime = 3600;
    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public int getRememberMeTime() {
        return rememberMeTime;
    }

    public void setRememberMeTime(int rememberMeTime) {
        this.rememberMeTime = rememberMeTime;
    }
}
