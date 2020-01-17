package com.imooc.dto;

/**
 * lcd  2019/12/30
 * Description:
 */
public class UserQueryCondition {
    private String username;
    private int age;
    private int ageTo;
    private int telphone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(int ageTo) {
        this.ageTo = ageTo;
    }

    public int getTelphone() {
        return telphone;
    }

    public void setTelphone(int telphone) {
        this.telphone = telphone;
    }

    @Override
    public String toString() {
        return "UserQueryCondition{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", ageTo=" + ageTo +
                ", telphone=" + telphone +
                '}';
    }
}
