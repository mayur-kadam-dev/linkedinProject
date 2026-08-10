package com.codingshuttle.linkedin.userService.utils;

import com.codingshuttle.linkedin.userService.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.mindrot.jbcrypt.BCrypt.*;

@Service
public class BCrypt {

    public static String hash(String s) {
        return hashpw(s, gensalt());
    }

    public static boolean match(String passwordText, String passwordHashed) {
        return checkpw(passwordText, passwordHashed);
    }
}
