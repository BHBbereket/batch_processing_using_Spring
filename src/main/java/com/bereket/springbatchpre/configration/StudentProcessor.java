package com.bereket.springbatchpre.configration;

import com.bereket.springbatchpre.entity.Student;
import org.springframework.batch.item.ItemProcessor;

public class StudentProcessor implements ItemProcessor<Student,Student> {
    @Override
    public Student process(Student student) throws Exception {
        return student;
    }
}
