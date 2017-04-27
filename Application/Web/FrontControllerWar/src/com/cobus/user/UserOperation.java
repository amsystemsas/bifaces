package com.cobus.user;

import com.cobus.user.model.User;
import com.cobus.user.model.UserProfile;
import com.cobus.user.service.UserProfileService;
import com.cobus.user.service.UserService;
import com.cobus.util.ComponentOperation;
import com.cobus.util.ErrorType;
import com.cobus.util.MessageUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Title: UserOperation.java <br>
 *
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 31/12/2016.
 */
@Controller
@ViewScoped
@ManagedBean(name = "userOperation")
public class UserOperation {

    private static final Logger log = LogManager.getLogger(UserOperation.class.getName());

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;

    /**
     * @param selectedUser
     */
    public void saveUser(User selectedUser) {
        if (userService.saveUser(selectedUser)) {
            MessageUtil.showMessage("Usuario registrado exitosamente", ErrorType.INFO);
            ComponentOperation.updateComponent("formUser:userDT");
        } else {
            MessageUtil.showMessage("Error guardando usuario", ErrorType.ERROR);
        }
    }

    /**
     *
     * @param selectedUser
     */
    public void updateUser(User selectedUser) {
        if (userService.updateUser(selectedUser)) {
            MessageUtil.showMessage("Usuario actualizando exitosamente", ErrorType.INFO);
            ComponentOperation.updateComponent("formUser:userDT");
            ComponentOperation.updateComponent("formUser:outNameUser");
        } else {
            MessageUtil.showMessage("Error actualizando usuario", ErrorType.ERROR);
        }
    }

    /**
     *
     * @param selectedUser
     */
    public void deleteUser(User selectedUser) {
        if (userService.deleteUserBySSO(selectedUser.getSsoId())) {
            MessageUtil.showModalMessage("Usuario eliminado exitosamente", ErrorType.INFO);
            ComponentOperation.updateComponent("formUser:userDT");
        } else {
            MessageUtil.showModalMessage("Error", ErrorType.INFO);
        }
    }

    /**
     *
     * @return
     */
    public List<User> loadAllUsers() {
        return userService.findAllUsers();
    }

    /**
     *
     * @return
     */
    public List<UserProfile> allProfile() {
        return userProfileService.findAll();
    }
}
