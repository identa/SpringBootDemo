package com.example.SpringBootDemo.controllers;

import com.example.SpringBootDemo.entities.UserEntity;
import com.example.SpringBootDemo.repositories.UserRepo;
import com.example.SpringBootDemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class SignInController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @GetMapping("sign-in")
    public String showSignUp(Model model) {
        model.addAttribute("user", new UserEntity());
        return "signin";
    }

    @PostMapping("success1")
    public String success(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          Model model,
                          HttpSession session) {
        int checkResult = userService.checkSignin(username, password);
        if (checkResult == 0) {
            model.addAttribute("error", "Incorrect username or password");
            return "signin";
        } else {
            model.addAttribute("message", "Success");
            session.setAttribute("UserName", username);
            return "redirect:/product";
        }
    }
}
