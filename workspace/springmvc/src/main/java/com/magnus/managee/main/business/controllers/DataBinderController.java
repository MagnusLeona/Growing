package com.magnus.managee.main.business.controllers;

import com.magnus.managee.main.business.entity.Dog;
import com.magnus.managee.main.business.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataBinderController {

    @RequestMapping("/data")
    public String index(User user1, Dog dog) {
        System.out.println(user1);
        System.out.println(dog);
        return "User got";
    }
}
