package com.zpy.bilibili.dao;


import com.zpy.bilibili.domain.User;
import com.zpy.bilibili.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {



    User getUserByPhone(String phone);

    Integer addUser(User user);

    Integer addUserInfo(UserInfo userInfo);
}
