package com.jnshu.service.impl;


import com.jnshu.entity.UserQv;
import com.jnshu.mapper.UserQvDao;
import com.jnshu.service.UserQvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userQvServieImpl")
public class UserQvServieImpl implements UserQvService{
    @Autowired
    UserQvDao userQvDao;

    public UserQv queryId(Long id){
        return userQvDao.findUserById(id);
    }

    public List<UserQv> queryName(String userQv){
        return userQvDao.findUserByName(userQv);
    }

    public boolean addUser(UserQv userQv)throws Exception{
        return userQvDao.addUser(userQv);
    }

    public boolean updateUser(UserQv userQv){
        return userQvDao.updateUser(userQv);
    }

    public boolean deleteUser(Long id){
        return userQvDao.deleteUser(id);
    }

    public boolean updateId(Long id, UserQv userQv){
        userQv.setId(id);
        return userQvDao.updateUser(userQv);
    }

    public List<UserQv> queryUser(UserQv userQv){
        return userQvDao.findUser(userQv);
    }

/*    public boolean addUserQv(UserQv userQv){
        return userQvDao.addUser(userQv);
    }

    public boolean updateUser(UserQv userQv){
        return userQvDao.updateUser(userQv);
    }

    public boolean deleteUser(int i){
        return userQvDao.deleteUser(i);
    }

    public List<UserQv> findUserByName(String userQv){
        return userQvDao.findUserByName(userQv);
    }

    public UserQv findUserById(int id){
        return userQvDao.findUserById(id);
    }

    public List<UserQv> findUser(UserQv userQv){
        return userQvDao.findUser(userQv);
    }
    public boolean updateUserId(int id,UserQv userQv){
        userQv.setId(id);
        return userQvDao.updateUser(userQv);
    }*/
}
