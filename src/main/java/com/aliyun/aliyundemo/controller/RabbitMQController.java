package com.aliyun.aliyundemo.controller;

import com.aliyun.aliyundemo.common.ReplyResult;
import com.aliyun.aliyundemo.rabbitmq.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {

    @Autowired
    private ProducerService producerService;

    @GetMapping(value = "api/aliyun/queue/direct/send/msg")
    public ReplyResult sendDirectMsg() {
        this.producerService.sendDirectMsg();
        return new ReplyResult();
    }

    @GetMapping(value = "api/aliyun/queue/topic/send/msg")
    public ReplyResult sendTopicMsg() {
        this.producerService.sendTopicMsg();
        return new ReplyResult();
    }

    @GetMapping(value = "api/aliyun/queue/fanout/send/msg")
    public ReplyResult sendFanoutMsg() {
        this.producerService.sendFanoutMsg();
        return new ReplyResult();
    }
}
