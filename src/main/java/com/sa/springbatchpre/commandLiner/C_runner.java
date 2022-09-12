package com.sa.springbatchpre.commandLiner;

import com.sa.springbatchpre.entity.Role;
import com.sa.springbatchpre.entity.User;
import com.sa.springbatchpre.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class C_runner implements CommandLineRunner {

    @Autowired
    private UserService userRepo;
    @Override
    public void run(String... args) throws Exception {
        User user=new User("bereket","bhb","123");
        Role role=new Role("student");
        Role role1=new Role("admin");

        role.setUser(user);
        role1.setUser(user);
        System.out.println("here are the entities");
        user.setRoles(Arrays.asList(role,role1));
        userRepo.addUser(user);
    }
}
