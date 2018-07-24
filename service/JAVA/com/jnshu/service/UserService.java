package com.jnshu.service;

import com.jnshu.entity.User;

public interface UserService {

    void regist(User user);

    User findUserByname(String name);

    User selectByuser(String name, String password);

    void updateTimeByid(Long id);

    void updateUser(User user);

    void updatetx(User user);

    User selectByid(Long id);

    User findMaiUser(String email);

    User findCodeUser(String code);

    User findIphone(String userIphone);

}
