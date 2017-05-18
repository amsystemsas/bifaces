package com.amsystem.bifaces.util.i18n.label;

import com.amsystem.bifaces.util.AbstractDao;
import com.amsystem.bifaces.util.i18n.case2.MessageTranslationPK;
import com.amsystem.bifaces.util.i18n.case2.MessageTranslation;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Title: MessageTranslationDao.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 10/03/2017.
 */

@Repository("messageTranslationDao")
public class MessageTranslationDao extends AbstractDao<Integer, MessageTranslation> implements IMessageTranslationDao {

    @Override
    public MessageTranslationDao findById(MessageTranslationPK pk) {
        return null;
    }

    @Override
    public void save(MessageTranslationDao messageTranslationDao) {

    }

    @Override
    public List<MessageTranslation> findAllMessage() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<MessageTranslation> messageTranslations = (List<MessageTranslation>) criteria.list();
        return messageTranslations;
    }
}
