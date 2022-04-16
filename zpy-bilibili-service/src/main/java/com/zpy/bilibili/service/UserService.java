package com.zpy.bilibili.service;

import com.mysql.cj.util.StringUtils;
import com.zpy.bilibili.dao.UserDao;
import com.zpy.bilibili.domain.User;
import com.zpy.bilibili.domain.UserInfo;
import com.zpy.bilibili.domain.constant.UserConstant;
import com.zpy.bilibili.domain.exception.ConditionException;
import com.zpy.bilibili.service.util.MD5Util;
import com.zpy.bilibili.service.util.RSAUtil;
import com.zpy.bilibili.service.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: zpy-bilibili
 * @description: 用户service
 * @author: 张鹏宇
 * @create: 2022-04-16 12:43
 **/

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void addUser(User user) {
        String phone = user.getPhone();
        if (StringUtils.isNullOrEmpty(phone)) {
            throw new ConditionException("手机号不能为空！");
        }

        User dbUser = this.getUserByPhone(phone);
        if (dbUser != null) {
            throw new ConditionException("手机号已经被注册!");
        }
        Date now = new Date();
        String salt = String.valueOf(now.getTime());
        String password = user.getPassword();
        String rawPassword;

        try {
            rawPassword = RSAUtil.decrypt(password);
        } catch (Exception e) {
            throw new ConditionException("密码解密失败!");
        }
        String md5Password = MD5Util.sign(rawPassword, salt, "UTF-8");
        user.setSalt(salt);
        user.setPassword(md5Password);
        user.setCreateTime(now);
        userDao.addUser(user);
        //添加用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setNick(UserConstant.DEFAULT_NICK); //使用用户的默认昵称
        userInfo.setBirth(UserConstant.DEFAULT_BIRTH); //默认出生日期
        userInfo.setGender(UserConstant.GENDER_MALE);
        userInfo.setCreateTime(now);
        userDao.addUserInfo(userInfo);
    }

    public User getUserByPhone(String phone) {
        return userDao.getUserByPhone(phone);
    }

    public String login(User user) {
        String phone = user.getPhone();
        if (StringUtils.isNullOrEmpty(phone)) {
            throw new ConditionException("手机号不能为空！");
        }
        User dbUser = this.getUserByPhone(phone);
        if (dbUser == null) {
            throw new ConditionException("当前用户信息不存在");
        }
        String password = user.getPassword();
        String rawPassword;
        try {
            rawPassword = RSAUtil.decrypt(password);
        } catch (Exception e) {
            throw new ConditionException("密码解密失败!");
        }
        String salt = dbUser.getSalt();
        String md5Pwd = MD5Util.sign(rawPassword, salt, "UTF-8");
        if (!md5Pwd.equals(dbUser.getPassword())) {
            throw new ConditionException("密码错误！");
        }
        TokenUtil tokenUtil = new TokenUtil();
        String s = tokenUtil.generateToken(dbUser.getId());
        return s;
    }
}
