package com.amsystem.bifaces.user.service;

import com.amsystem.bifaces.user.model.User;

import java.util.List;

/**
 * Title: UserService.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */
public interface UserService {

    User findById(int id);

    User findBySSO(String sso);

    boolean saveUser(User user);

    boolean updateUser(User user);

    boolean deleteUserBySSO(String sso);

    List<User> findAllUsers();

    boolean isUserSSOUnique(Integer id, String sso);

}
