package com.aliyun.aliyundemo.controller;

import com.aliyun.aliyundemo.zookeeper.WatcherApi;
import com.aliyun.aliyundemo.zookeeper.ZookeeperService;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ZookeeperController {

    @Autowired
    private ZookeeperService zookeeperService;

    @GetMapping(value = "api/aliyun/zk/node/exist")
    public Stat existNode(String path) {
        return this.zookeeperService.exists(path, new WatcherApi());
    }

    @GetMapping(value = "api/aliyun/zk/node/create")
    public boolean createNode(String path, String value) {
        return this.zookeeperService.createNode(path, value);
    }

    @GetMapping(value = "api/aliyun/zk/get-children/nodes")
    public List<String> getChildren(String path) throws KeeperException, InterruptedException {
        return this.zookeeperService.getChildren(path);
    }
}
