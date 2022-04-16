package com.zpy.bilibili.api;

import com.zpy.bilibili.domain.JsonResponse;
import com.zpy.bilibili.domain.User;
import com.zpy.bilibili.service.UserService;
import com.zpy.bilibili.service.util.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.interfaces.RSAPublicKey;

/**
 * @program: zpy-bilibili
 * @description: 用户接口
 * @author: 张鹏宇
 * @create: 2022-04-16 12:43
 **/

@RestController
public class UserApi {

    @Autowired
    private UserService userService;


    @GetMapping("/rsa-pks")
    public JsonResponse<String> getRsaPublicKey(){
        String pk = RSAUtil.getPublicKeyStr();

        return new JsonResponse<>(pk);

    }

    /**
     * 注册接口
     * @param user
     * @return
     */
    @PostMapping("/user")
    public JsonResponse<String> addUser(@RequestBody User user){
        userService.addUser(user);
        return JsonResponse.success();

    }

    @PostMapping("/user-tokens")
    public JsonResponse<String> login(@RequestBody User user){
        String token = userService.login(user);
        return new JsonResponse<>(token);
    }

}
