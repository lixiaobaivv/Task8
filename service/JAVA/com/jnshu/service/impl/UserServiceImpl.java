package com.jnshu.service.impl;

import com.jnshu.entity.User;
import com.jnshu.mapper.UserDao;
import com.jnshu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("userServiceImpl")
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    //注册

    @Override
    public void regist(User user){
        userDao.register(user);
    }

    //查询账户名密码

    @Override
    public User selectByuser(String name, String password){
        return userDao.selectByuser(name,password);
    }

    //查询姓名

    @Override
    public User findUserByname(String name){
        return userDao.findUserByname(name);
    }


    @Override
    public User selectByid(Long id) {
        return userDao.selectByid(id);
    }

    @Override
    public void updateTimeByid(Long id){
         userDao.updateTimeByid(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
    @Override
    public void updatetx(User user){
        userDao.updatetx(user);
    }

    @Override
    public User findIphone(String userIphone){
        return userDao.findIphone(userIphone);
    }

    @Override
    public User findMaiUser(String email){
        return userDao.findMaiUser(email);
    }
    @Override

    public User findCodeUser(String code){
        return userDao.findCodeUser(code);
    }


}
