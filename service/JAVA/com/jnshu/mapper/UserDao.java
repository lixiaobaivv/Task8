package com.jnshu.mapper;

import com.jnshu.entity.User;

public interface UserDao {

    //登录 和 注册

    User findUserByname(String name);

    public void register(User user);

    User selectByid(Long id);

    User selectByuser(String name, String password);

    void updateTimeByid(Long id);

    void updateUser(User user);

    void updatetx(User user);

    User findMaiUser(String email);

    User findCodeUser(String code);

    User findIphone(String userIphone);
}
