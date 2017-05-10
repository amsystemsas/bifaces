package com.amsystem.bifaces.user.service.impl;

import com.amsystem.bifaces.user.service.UserService;
import com.amsystem.bifaces.user.dao.IUserDao;
import com.amsystem.bifaces.user.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: UserServiceImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private IUserDao dao;

    private boolean flag;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findById(int id) {
        return dao.findById(id);
    }

    public User findBySSO(String sso) {
        User user = dao.findBySSO(sso);
        return user;
    }

    public boolean saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return dao.save(user);
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     */
    public boolean updateUser(User user) {
        User entity = dao.findById(user.getUserId());
        flag = true;

        try {
            if (entity != null) {
                entity.setUserName(user.getUserName());
                if (!user.getPassword().equals(entity.getPassword())) {
                    entity.setPassword(passwordEncoder.encode(user.getPassword()));
                }
                entity.setFirstName(user.getFirstName());
                entity.setLastName(user.getLastName());
                entity.setEmail(user.getEmail());
                entity.setUserProfiles(user.getUserProfiles());
            }

        } catch (Exception e) {
            flag = false;
            log.error("Error: " + e.getMessage());

        }

        return flag;
    }


    public boolean deleteUserBySSO(String sso) {
        return dao.deleteBySSO(sso);
    }

    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }

    public boolean isUserSSOUnique(Integer id, String sso) {
        User user = findBySSO(sso);
        return (user == null || ((id != null) && (user.getUserId() == id)));
    }

}

