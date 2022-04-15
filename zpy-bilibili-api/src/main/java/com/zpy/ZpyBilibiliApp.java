package com.zpy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @program: zpy-bilibili
 * @description: Springboot启动器
 * @author: 张鹏宇
 * @create: 2022-04-15 23:56
 **/

@SpringBootApplication
public class ZpyBilibiliApp {
    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(ZpyBilibiliApp.class,args);
    }
}
