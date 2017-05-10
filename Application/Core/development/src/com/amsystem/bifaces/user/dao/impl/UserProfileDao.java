package com.amsystem.bifaces.user.dao.impl;

import com.amsystem.bifaces.util.AbstractDao;
import com.amsystem.bifaces.user.dao.IUserProfileDao;
import com.amsystem.bifaces.user.model.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Title: UserProfileDaoImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */

@Repository("userProfileDao")
public class UserProfileDao extends AbstractDao<Integer, UserProfile> implements IUserProfileDao {

    @Transactional(readOnly = false)
    public UserProfile findById(int id) {
        return getByKey(id);
    }

    @Transactional(readOnly = false)
    public UserProfile findByType(String type) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("type", type));
        return (UserProfile) crit.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = false)
    public List<UserProfile> findAll(){
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("type"));
        return (List<UserProfile>)crit.list();
    }

}

