package com.imooc.sucurity.core.validate.code.bean;

/**
 * lcd  2020/1/17
 * Description:
 */
public enum ValidateCodeType {
    IMAGE,SMS;

    public static void main(String[] args) {
        System.out.println(ValidateCodeType.SMS.name());
        System.out.println(ValidateCodeType.IMAGE.name());
    }

}
