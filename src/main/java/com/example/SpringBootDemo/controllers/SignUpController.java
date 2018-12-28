package com.example.SpringBootDemo.controllers;

import com.example.SpringBootDemo.entities.UserEntity;
import com.example.SpringBootDemo.repositories.UserRepo;
import com.example.SpringBootDemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/")
public class SignUpController {
    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @GetMapping("sign-up")
    public String showSignUp(Model model) {
        model.addAttribute("user", new UserEntity());
        return "signup";
    }

    @PostMapping("success")
    public String success(UserEntity userEntity, Model model, @RequestParam("re-password") String rePassword) {
        if (userService.isUsernameOrEmailExist(userEntity.getUsername(), userEntity.getEmail())) {
            if (userService.isPasswordFormatCorrect(userEntity.getPassword())) {
                if (userService.isPasswordChecked(userEntity.getPassword(), rePassword)) {
                    userRepo.save(userEntity);
                    model.addAttribute("user", userEntity.getUsername());
                    return "success";
                } else {
                    model.addAttribute("user", "Password no matched");
                    return "success";
                }
            } else {
                model.addAttribute("user", "Error password");
                return "success";
            }
        } else {
            model.addAttribute("user", "Error usr n em");
            return "success";
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
