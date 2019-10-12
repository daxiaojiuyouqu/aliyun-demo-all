package com.aliyun.aliyundemo.service.impl;

import com.aliyun.aliyundemo.cache.StudentRedisCacheService;
import com.aliyun.aliyundemo.dao.StudentMapper;
import com.aliyun.aliyundemo.domain.Student;
import com.aliyun.aliyundemo.service.IStudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentRedisCacheService studentRedisCacheService;

    @Autowired
    private StudentTransactionService studentTransactionService;

    @Override
    public int create(Student record) {
        int row = this.studentMapper.insert(record);
        if (row == 1) {
            Long id = record.getId();
            if (Objects.nonNull(id)) {
                studentRedisCacheService.set(id, record);
            }
        }
        return row;
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
        Student student = this.studentRedisCacheService.get(id);
        if (Objects.nonNull(student)) {
            return student;
        }
        student = this.studentMapper.selectByPrimaryKey(id);
        this.studentRedisCacheService.set(id, student);
        return student;
    }

    @Override
    public PageInfo<Student> findByPage(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Student> list = this.studentMapper.list();
        return new PageInfo<>(list);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    @Override
    public List<Student> testTransaction(Student student) {
        this.studentTransactionService.insert(student);
        List<Student> list = this.studentMapper.list();
        log.info("学生列表:{}", new Gson().toJson(list));
        return list;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void insertStudent(Student student) {
        this.studentMapper.insert(student);
    }


}
