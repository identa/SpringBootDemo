package com.example.SpringBootDemo.controllers;

import com.example.SpringBootDemo.entities.UserEntity;
import com.example.SpringBootDemo.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ProductShowController {
    @Autowired
    ProductRepo productRepo;
    @GetMapping("product")
    public String showProduct(Model model, HttpSession session) {
        String username = (String) session.getAttribute("UserName");
        model.addAttribute("product", productRepo.findByUserEntityUsername(username) );
        return "product";
    }

}
