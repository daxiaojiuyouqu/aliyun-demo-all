package com.aliyun.aliyundemo.service.impl;

import com.aliyun.aliyundemo.dao.StudentMapper;
import com.aliyun.aliyundemo.domain.Student;
import com.aliyun.aliyundemo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int create(Student record) {
        return studentMapper.insert(record);
    }

    @Override
    public int delete(Long id) {
        return this.studentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Student record) {
        return this.studentMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Student> list() {
        return this.studentMapper.list();
    }

    @Override
    public Student getById(Long id) {
        return this.studentMapper.selectByPrimaryKey(id);
    }
}
