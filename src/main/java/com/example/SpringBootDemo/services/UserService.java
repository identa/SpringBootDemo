package com.example.SpringBootDemo.services;

import com.example.SpringBootDemo.constants.SignUpConst;
import com.example.SpringBootDemo.entities.UserEntity;
import com.example.SpringBootDemo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService implements SignUpConst {

    @Autowired
    UserRepo userRepo;

    public int checkSignin(String username, String password) {
        UserEntity userEntity = userRepo.findByUsernameAndPassword(username, password);
        if (userEntity == null) return 0;
        else return 1;
    }

    public boolean isUsernameOrEmailExist(String username, String email) {
        UserEntity userEntity = userRepo.findByUsernameOrEmail(username, email);
        return userEntity == null ? true : false;
    }

    public boolean isPasswordFormatCorrect(String password) {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(PASSWORD_REGEX);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean isPasswordChecked(String password, String rePassword) {
        return password.equals(rePassword) ? true : false;
    }

    public boolean isRememberMeChecked(String rememberMe){
        return  (rememberMe == "true") ? true : false;
    }

    public void rememberMe(String password, HttpServletResponse response){
        Cookie cookie = new Cookie("cookie", password);
        cookie.setMaxAge(100000);
        response.addCookie(cookie);
    }
}

