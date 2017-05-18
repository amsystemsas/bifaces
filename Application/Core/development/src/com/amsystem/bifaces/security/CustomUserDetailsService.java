package com.amsystem.bifaces.security;

import com.amsystem.bifaces.UserInfo;
import com.amsystem.bifaces.user.model.User;
import com.amsystem.bifaces.user.model.UserProfile;
import com.amsystem.bifaces.user.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: CustomUserDetailsService.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 12/09/2016.
 */

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger log = LogManager.getLogger(CustomUserDetailsService.class.getName());

    @Autowired
    private UserService userService;



    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String ssoId)
            throws UsernameNotFoundException {
        User user = userService.findBySSO(ssoId);

        if(user==null){
            log.info("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        UserInfo.setLoginUser(user.getUserName());
        //UserInfo.getUserInfo(user.getUserName(),new Locale("es"));
        //userInfo.setLocale(new Locale("es"));
        //userInfo.setLogin(user.getUserName());
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                true, true, true, true, getGrantedAuthorities(user));
    }


    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();

        for(UserProfile userProfile : user.getUserProfiles()){
            log.info("UserProfile : {}" + userProfile);
            authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
        }
        log.info("authorities : {}" + authorities);
        return authorities;
    }

}
