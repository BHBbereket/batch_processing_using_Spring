package com.bereket.springbatchpre.controller;

import com.bereket.springbatchpre.entity.User;
import com.bereket.springbatchpre.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class Controller {
    @Autowired
    private UserService userService;
    @PostMapping("/upload_data/csv")
    public void upload(){
        userService.getJobDone();
    }
    @PostMapping("/user/save/adduser")
    public User saveUser(@RequestBody User user){
        return userService.addUser(user);
    }


}
