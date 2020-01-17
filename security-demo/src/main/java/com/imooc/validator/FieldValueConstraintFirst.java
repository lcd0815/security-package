package com.imooc.validator;

import com.imooc.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * lcd  2020/1/1
 * Description:
 */
public class FieldValueConstraintFirst implements ConstraintValidator <UsernameValidator,Object>{
    @Autowired
    private HelloService helloService;
    @Override
    public void initialize(UsernameValidator constraintAnnotation) {
        System.out.println("ConstraintValidator init");
    }
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println("开始校验"+value);
//        System.out.println(helloService.hello(value.toString()));
        return false;
    }
}
