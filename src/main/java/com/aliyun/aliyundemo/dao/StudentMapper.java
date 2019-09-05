package com.aliyun.aliyundemo.dao;

import com.aliyun.aliyundemo.domain.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("studentMapper")
public interface StudentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> list();

    void deleteAll();
}