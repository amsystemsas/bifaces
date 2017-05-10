package com.amsystem.bifaces.user.bean;

import com.amsystem.bifaces.user.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Pattern;

/**
 * Title: UserBean.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 05/01/2017.
 */
@ViewScoped
@ManagedBean(name = "userBean")
public class UserBean extends User {

    public UserBean() {
        super();
    }

    @Pattern(regexp = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,})$")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
