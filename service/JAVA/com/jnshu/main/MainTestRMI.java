package com.jnshu.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTestRMI {
    public static void main(String args[]){
        new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("服务端RMI启动 端口号9998");
    }
}
