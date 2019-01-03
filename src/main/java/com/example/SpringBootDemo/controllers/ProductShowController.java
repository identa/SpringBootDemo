package com.example.SpringBootDemo.controllers;

import com.example.SpringBootDemo.entities.ProductEntity;
import com.example.SpringBootDemo.entities.UserEntity;
import com.example.SpringBootDemo.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ProductShowController {
    @Autowired
    ProductRepo productRepo;

    @GetMapping("product")
    public String showProduct(Model model, HttpSession session) {
        String username = (String) session.getAttribute("UserName");
        model.addAttribute("product", productRepo.findByUserEntityUsername(username));
        return "product";
    }

    @GetMapping("product/edit/{id}")
    public String showProductDetail(Model model,
                                    @PathVariable int id) {
        model.addAttribute("product", productRepo.findByProductId(id));
        model.addAttribute("action", "/product/update");
        return "edit";
    }

    @PostMapping(value = "product/update")
    public String update(@ModelAttribute ProductEntity productEntity) {
        productRepo.save(productEntity);
        return "redirect:/product";
    }


    @GetMapping(value = "product/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        productRepo.deleteById(id);
        return "redirect:/product";
    }
}