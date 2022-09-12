package com.sa.springbatchpre.repository;


import com.sa.springbatchpre.entity.Role;
import com.sa.springbatchpre.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    @Query(value = "select u from User u where u.username=?1")
    User findUserByUsername(String username);
    @Query(value = "select r from User u join Role r where u=r.user and u.username=?1")
    List<Role> findByUsername(String username);
    @Query(value = "select user from User user where user.id=?1")
    User findUserById(int user_id);
}
