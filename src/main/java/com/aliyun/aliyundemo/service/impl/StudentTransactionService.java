package com.aliyun.aliyundemo.service.impl;

import com.aliyun.aliyundemo.dao.StudentMapper;
import com.aliyun.aliyundemo.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentTransactionService {

    @Autowired
    private StudentMapper studentMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public void insert(Student student) {
        this.studentMapper.insert(student);
    }
}
