package com.aliyun.aliyundemo.controller;

import com.aliyun.aliyundemo.mongo.model.MongoUser;
import com.aliyun.aliyundemo.mongo.service.MongoUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class MongoUserController {

    @Autowired
    private MongoUserService mongoUserService;

    @PostMapping(value = "api/aliyun/mongo/user/save")
    public void save(@RequestBody MongoUser user) {
        this.mongoUserService.save(user);
    }

    @PostMapping(value = "api/aliyun/mongo/user/save/batch")
    public void saveBatch(@RequestBody List<MongoUser> users) {
        this.mongoUserService.saveBatch(users);
    }

    @GetMapping(value = "api/aliyun/mongo/user/find-by-id")
    public MongoUser findById(Long id) {
        return this.mongoUserService.findById(id);
    }

    @GetMapping(value = "api/aliyun/mongo/user/list")
    public List<MongoUser> list() {
        return this.mongoUserService.list();
    }

    @PostMapping(value = "api/aliyun/mongo/user/update")
    public void update(@RequestBody MongoUser user) {
        this.mongoUserService.update(user);
    }
}
