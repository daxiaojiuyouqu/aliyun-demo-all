package com.aliyun.aliyundemo.controller;

import com.aliyun.aliyundemo.cache.StudentGuavaCacheService;
import com.aliyun.aliyundemo.common.ReplyResult;
import com.aliyun.aliyundemo.domain.Student;
import com.aliyun.aliyundemo.service.IStudentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private StudentGuavaCacheService studentGuavaCacheService;

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

    @RequestMapping(value = "api/aliyun/student/page/list")
    public PageInfo<Student> page(int pageNo, int pageSize) {
        return this.studentService.findByPage(pageNo, pageSize);
    }

    @GetMapping(value = "api/aliyun/student/get-by-pk")
    public Student getByPk(Long id) {
        return this.studentGuavaCacheService.getByPk(id);
    }

    @GetMapping(value = "api/aliyun/student/delete-by-pk")
    public void deleteByPk(Long id) {
        this.studentGuavaCacheService.deleteByPk(id);
    }

    @PostMapping(value = "api/aliyun/student/update")
    public void update(@RequestBody Student student) {
        this.studentGuavaCacheService.update(student);
    }

    @PostMapping(value = "api/aliyun/student/test/transaction")
    public ReplyResult<List<Student>> testTransaction(@RequestBody Student student) {
        return new ReplyResult<>(this.studentService.testTransaction(student));
    }

    @PostMapping(value = "api/aliyun/student/test/transaction2")
    public void testTransaction2(@RequestBody Student student) {
        this.studentService.insertStudent(student);
    }

}
