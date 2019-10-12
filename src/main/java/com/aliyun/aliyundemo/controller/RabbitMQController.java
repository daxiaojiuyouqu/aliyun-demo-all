package com.aliyun.aliyundemo.controller;

import com.aliyun.aliyundemo.common.ReplyResult;
import com.aliyun.aliyundemo.rabbitmq.chuxue.model.QueueUser;
import com.aliyun.aliyundemo.rabbitmq.chuxue.service.amqptemplate.ProducerService;
import com.aliyun.aliyundemo.rabbitmq.chuxue.service.rabbittemplate.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


    /************************************rabbitTemplate******************************************/

    @Autowired
    private Producer producer;

    @PostMapping(value = "api/aliyun/queue/rabbit/send/obj/msg")
    public ReplyResult rabbitSendObjMsg(@RequestBody QueueUser user) {
        this.producer.sendMsg(user);
        return new ReplyResult();
    }

    @GetMapping(value = "api/aliyun/queue/rabbit/send/str/msg")
    public ReplyResult rabbitSendStrMsg(String str) {
        this.producer.sendMsg(str);
        return new ReplyResult();
    }
}
