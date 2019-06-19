package com.aliyun.aliyundemo.service;

import com.aliyun.aliyundemo.domain.Student;

import java.util.List;

public interface IStudentService {

    int create(Student record);

    int delete(Long id);

    int update(Student record);

    List<Student> list();

    Student getById(Long id);
}
