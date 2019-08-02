package com.aliyun.aliyundemo.controller;

import com.aliyun.aliyundemo.common.ReplyResult;
import com.aliyun.aliyundemo.domain.Student;
import com.aliyun.aliyundemo.service.IStudentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @RequestMapping(value = "api/aliyun/student/create")
    public ReplyResult create(@RequestBody Student student) {
        this.studentService.create(student);
        return new ReplyResult();
    }

    @RequestMapping(value = "api/aliyun/student/list")
    public ReplyResult<List<Student>> list() {
        List<Student> students = this.studentService.list();
        return new ReplyResult<>(students);
    }

    @RequestMapping(value = "api/aliyun/student/get")
    public ReplyResult<Student> get(Long id) {
        Student student = this.studentService.getById(id);
        return new ReplyResult<>(student);
    }

    @RequestMapping(value = "api/aliyun/student/fen-ye/list")
    public PageInfo<Student> fenYe(int pageNo, int pageSize) {
        return this.studentService.findByPage(pageNo, pageSize);
    }

}
