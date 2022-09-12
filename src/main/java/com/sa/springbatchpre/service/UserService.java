package com.sa.springbatchpre.service;

import com.sa.springbatchpre.entity.Role;
import com.sa.springbatchpre.entity.User;
import com.sa.springbatchpre.repository.RoleRepo;
import com.sa.springbatchpre.repository.UserRepo;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@org.springframework.stereotype.Service
public class UserService implements UserDetailsService {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void getJobDone(){
        JobParameters jobParameters=new JobParametersBuilder()
                .addLong("startAt",System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(job,jobParameters);
        }catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException | JobRestartException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            User user= userRepo.findUserByUsername(username);
            if(user==null)
                throw new UsernameNotFoundException("user not found");
            else{
                System.out.println("user found by :"+username);
            }
            Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });

            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
        }

    public User addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);

    }
    public Role addRole(Role role){
        return roleRepo.save(role);
    }
    public User getuser(String username){
        return userRepo.findUserByUsername(username);
    }
    public User getUser(int user_id){
        return userRepo.findUserById(user_id);
    }
    public List<User> getUsers(){
        return userRepo.findAll();
    }
    public List<Role> getRoles(String username){
        return userRepo.findByUsername(username);
    }

}
