package com.jnshu.service;


import com.jnshu.entity.Student;
import java.util.List;

public interface StudentService {

    List<Student> findAll();

    Student getID(Long id);

}
