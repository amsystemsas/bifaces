package com.amsystem.bifaces.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Title: QuickPasswordEncodingGenerator.java <br>
 *
 * @author Jaime Aguilar (JAR)
 *         File Creation on 19/09/2016.
 */
public class QuickPasswordEncodingGenerator {

    public static void main(String[] args) {
        String password = "admin";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode(password));
    }
}
