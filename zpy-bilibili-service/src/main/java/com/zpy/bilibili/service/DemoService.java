package com.zpy.bilibili.service;

import com.zpy.bilibili.dao.DemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program: zpy-bilibili
 * @description: DemoService
 * @author: 张鹏宇
 * @create: 2022-04-16 00:40
 **/

@Service
public class DemoService {

    @Autowired
    private DemoDao demoDao;

    public Long query(Long id) {
        return demoDao.query(id);
    }
}
