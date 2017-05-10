package com.amsystem.bifaces.user.dao;

import com.amsystem.bifaces.user.model.User;

import java.util.List;

/**
 * Title: UserDao.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */
public interface IUserDao {

    User findById(int id);

    User findBySSO(String sso);

    boolean save(User user);

    boolean deleteBySSO(String sso);

    List<User> findAllUsers();
}
