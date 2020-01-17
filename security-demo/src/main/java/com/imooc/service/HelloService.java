package com.imooc.service;

import org.springframework.stereotype.Service;

/**
 * lcd  2020/1/1
 * Description:
 */
@Service
public class HelloService {
    public String hello(String msg){
        System.out.println("greeting");
        return msg + "hahaha";
    }
}
