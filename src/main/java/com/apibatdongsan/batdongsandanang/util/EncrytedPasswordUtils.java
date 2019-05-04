package com.apibatdongsan.batdongsandanang.util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncrytedPasswordUtils {
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static boolean checkMatchPassword(String password, String encrytedPassword) {
        return BCrypt.checkpw(password, encrytedPassword);
    }
}
