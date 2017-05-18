package com.amsystem.bifaces.util;

import com.amsystem.bifaces.user.model.PersistentLogin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Title: HibernateTokenRepositoryImpl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */

@Repository("tokenRepository")
@Transactional
public class HibernateTokenRepositoryImpl extends AbstractDao<String, PersistentLogin>  implements PersistentTokenRepository {

    private static final Logger log = LogManager.getLogger(HibernateTokenRepositoryImpl.class.getName());

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        log.info("Creating Token for user : {}" + token.getUsername());
        PersistentLogin persistentLogin = new PersistentLogin();
        persistentLogin.setUserName(token.getUsername());
        persistentLogin.setSeries(token.getSeries());
        persistentLogin.setToken(token.getTokenValue());
        persistentLogin.setLastUsed(token.getDate());
        persist(persistentLogin);

    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        log.info("Fetch Token if any for seriesId : {}" + seriesId);
        try {
            Criteria crit = createEntityCriteria();
            crit.add(Restrictions.eq("series", seriesId));
            PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();

            return new PersistentRememberMeToken(persistentLogin.getUserName(), persistentLogin.getSeries(),
                    persistentLogin.getToken(), persistentLogin.getLastUsed());
        } catch (Exception e) {
            log.info("Token not found...");
            return null;
        }
    }

    @Override
    public void removeUserTokens(String username) {
        log.info("Removing Token if any for user : {}" + username);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("userName", username));
        PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();
        if (persistentLogin != null) {
            log.info("rememberMe was selected");
            delete(persistentLogin);
        }

    }

    @Override
    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
        log.info("Updating Token for seriesId : {}" + seriesId);
        PersistentLogin persistentLogin = getByKey(seriesId);
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLastUsed(lastUsed);
        update(persistentLogin);
    }

}

