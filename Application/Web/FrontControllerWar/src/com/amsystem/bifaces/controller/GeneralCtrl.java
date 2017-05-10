package com.amsystem.bifaces.controller;

import com.amsystem.bifaces.user.model.User;
import com.amsystem.bifaces.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Title: GeneralCtrl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 24/03/2017.
 */

@Controller
@RequestMapping("/")
public class GeneralCtrl {

    @Autowired
    UserService userService;


    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    protected String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        User user = userService.findBySSO(userName);
        return user.getFirstName().concat(" ").concat(user.getLastName());
    }
}
