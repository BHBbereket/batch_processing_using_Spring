package com.bereket.springbatchpre.entity;


import javax.persistence.*;

@Entity
@Table(name = "role_table")
public class Role {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Role() {
    }

    public Role(String name) {
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
