package com.jnshu.service;

import com.jnshu.entity.UserQv;

import java.util.List;

public interface UserQvService {


    UserQv queryId(Long id);

    List<UserQv> queryName(String userQv);

    boolean addUser(UserQv userQv)throws Exception;

    boolean updateUser(UserQv userQv);

    boolean deleteUser(Long id);

    boolean updateId(Long id, UserQv userQv);

    List<UserQv> queryUser(UserQv userQv);

/*    boolean addUserQv(UserQv userQv);

    boolean updateUser(UserQv userQv);

    boolean deleteUser(int i);

    List<UserQv> findUserByName(String userQv);

    UserQv findUserById(int id);

    List<UserQv> findUser(UserQv userQv);

    boolean updateUserId(int id,UserQv userQv)throws Exception;*/
}
