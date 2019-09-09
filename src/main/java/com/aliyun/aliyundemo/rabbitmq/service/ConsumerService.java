package com.aliyun.aliyundemo.rabbitmq.service;

import com.aliyun.aliyundemo.rabbitmq.config.RabbitMQConfig;
import com.aliyun.aliyundemo.rabbitmq.model.QueueUser;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    /**
     * 监听名字为RabbitMQConfig.DIRECT_QUEUE_NAME的消息队列
     */
    @RabbitListener(queues = RabbitMQConfig.DIRECT_QUEUE_NAME)
    public void receiveDirectMsg(QueueUser user) {
        log.info("我是direct-queue,监听到消息:{}", new Gson().toJson(user));
    }


    /**
     * 监听名为RabbitMQConfig.TOPIC_QUEUE_NAME1的消息队列
     *
     * @param user
     */
    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE_NAME1)
    public void receiveTopic1Msg(QueueUser user) {
        log.info("我是topic-queue1,监听到消息:{}", new Gson().toJson(user));
    }

    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE_NAME2)
    public void receiveTopic2Msg(QueueUser user) {
        log.info("我是topic-queue2,监听到消息:{}", new Gson().toJson(user));
    }


}
