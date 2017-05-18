package com.amsystem.bifaces.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Title: MenuCtrl.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 24/03/2017.
 */

@Controller
@RequestMapping("/")
public class MenuCtrl extends GeneralCtrl {

    private static final Logger log = LogManager.getLogger(MenuCtrl.class.getName());
    /**
     * This method will list all existing users.
     */
    @RequestMapping(value = {"/", "/home"}, method = {RequestMethod.GET})
    public String listUsers(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        //logs debug message
        if (log.isDebugEnabled()) {
            log.debug("getWelcome is executed!");
        }
        //TODO: Cargar configuracion de Menu segun el usuario
        return "index";
    }

    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = {"/userTool"}, method = RequestMethod.GET)
    public String userTool(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "usertool/userTool";
    }


    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = {"/propertyTemplateTool"}, method = RequestMethod.GET)
    public String goPropertyTemplateTool(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "templatetool/templateTool";
    }


    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = {"/productTool"}, method = RequestMethod.GET)
    public String goProductTool(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "templatetool/templateTool";
    }

    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = {"/testTemplateGenerator"}, method = RequestMethod.GET)
    public String goTestGenerator(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "generator/templateGenerator";
    }

    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = {"/menuviejo"}, method = RequestMethod.GET)
    public String goTestAdminFaces(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "menu";
    }

}
