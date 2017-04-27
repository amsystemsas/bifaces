package com.cobus.util.i18n.case2;

/**
 * Title: MessageResource.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 22/08/2016.
 */

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="MESSAGETRANSLATION")
public class MessageTranslation implements GenericMessageTranslation {

    @Id
    private MessageTranslationPK messageTranslationPK;

    @NotEmpty
    @Column(name="MESSAGE", nullable=false)
    private String message;

    public MessageTranslationPK getMessageTranslationPK() {
        return messageTranslationPK;
    }

    public void setMessageTranslationPK(MessageTranslationPK messageTranslationPK) {
        this.messageTranslationPK = messageTranslationPK;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getKey() {
        return messageTranslationPK.getKey();
    }

    public String getCategory() {
        return messageTranslationPK.getCategory();
    }

    public String getLocale() {
        return messageTranslationPK.getLocale();
    }


}
