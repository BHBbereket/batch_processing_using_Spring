package com.bereket.springbatchpre.repository;

import com.bereket.springbatchpre.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {
    @Query(value = "select r from Role r where r.name=?1")
    Role getRoleByName(String name);
}
