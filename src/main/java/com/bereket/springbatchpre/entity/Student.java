package com.bereket.springbatchpre.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "student_table")
public class Student {
    @Id
    private  int id;
    private String firstname;
    private String lastname;
    private int age;
    private double gpa;
}
