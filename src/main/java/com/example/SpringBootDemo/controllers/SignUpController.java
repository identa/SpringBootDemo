package com.example.SpringBootDemo.controllers;

import com.example.SpringBootDemo.entities.UserEntity;
import com.example.SpringBootDemo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class SignUpController {
    @Autowired
    UserRepo userRepo;

    @GetMapping("sign-up")
    public String showSignUp(Model model) {
        model.addAttribute("user", new UserEntity());
        return "signup";
    }

    @PostMapping("success")
    public String success(UserEntity userEntity, Model model) {
        userRepo.save(userEntity);
        model.addAttribute("user", userEntity.getUsername());
        return "success";
    }

}
