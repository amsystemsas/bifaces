package com.amsystem.bifaces.user.service;

import com.amsystem.bifaces.user.model.UserProfile;

import java.util.List;

/**
 * Title: UserProfileService.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */

public interface UserProfileService {

    UserProfile findById(int id);

    UserProfile findByType(String type);

    List<UserProfile> findAll();

}
