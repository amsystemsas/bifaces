package com.amsystem.bifaces.user.converter;

import com.amsystem.bifaces.user.model.UserProfile;
import com.amsystem.bifaces.user.service.UserProfileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Title: RoleToUserProfileConverter.java <br>
 * Clase que se encargará de asignar los identificadores individuales de perfil de usuario a las
 * entidades de perfil de usuario reales en la base de datos.
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 08/09/2016.
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile> {
    private static final Logger log = LogManager.getLogger(RoleToUserProfileConverter.class.getName());

	@Autowired
    UserProfileService userProfileService;

    /**
     * Gets UserProfile by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public UserProfile convert(Object element) {
        Integer id = Integer.parseInt((String)element);
        UserProfile profile= userProfileService.findById(id);
        log.info("Profile : {}" + profile);
        return profile;
    }
}
