package com.sa.springbatchpre.configration;

import com.sa.springbatchpre.entity.Student;
import org.springframework.batch.item.ItemProcessor;

public class StudentProcessor implements ItemProcessor<Student,Student> {
    @Override
    public Student process(Student student) throws Exception {
        return student;
    }
}
