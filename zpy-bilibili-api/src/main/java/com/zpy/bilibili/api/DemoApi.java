package com.zpy.bilibili.api;

import com.zpy.bilibili.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: zpy-bilibili
 * @description: DemoApi
 * @author: 张鹏宇
 * @create: 2022-04-16 00:42
 **/
@RestController
public class DemoApi {

    @Autowired
    private DemoService demoService;


    @GetMapping("/query")
    public Long query(Long id){
        return demoService.query(id);
    }
}
