package com.jnshu.mapper;


import com.jnshu.entity.UserQv;

import java.util.List;

public interface UserQvDao {

    boolean addUser(UserQv userQv)throws Exception;

    boolean updateUser(UserQv userQv);

    boolean deleteUser(Long i);

    List<UserQv> findUserByName(String userQv);

    UserQv findUserById(Long id);

    List<UserQv> findUser(UserQv userQv);


}
