package com.example.SpringBootDemo.services;

import com.example.SpringBootDemo.entities.UserEntity;
import com.example.SpringBootDemo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public int checkSignin(String username, String password){
        UserEntity userEntity = userRepo.findByUsernameAndPassword(username, password);
        if (userEntity == null) {
            return 0;
        } else return 1;
    }

}
