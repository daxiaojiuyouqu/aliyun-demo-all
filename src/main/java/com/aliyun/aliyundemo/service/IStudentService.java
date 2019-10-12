package com.aliyun.aliyundemo.service;

import com.aliyun.aliyundemo.domain.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IStudentService {

    int create(Student record);

    int delete(Long id);

    int update(Student record);

    List<Student> list();

    Student getById(Long id);

    PageInfo<Student> findByPage(Integer pageNo, Integer pageSize);

    List<Student> testTransaction(Student student);

    void insertStudent(Student student);


}
