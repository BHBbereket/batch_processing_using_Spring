package com.bereket.springbatchpre.controller;

import com.bereket.springbatchpre.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class Controller {
    @Autowired
    private Service service;
    @PostMapping("/upload_data")
    public void upload(){
        service.getJobDone();
    }
}
