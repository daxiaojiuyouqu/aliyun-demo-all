package com.aliyun.aliyundemo.rabbitmq.chuxue.service.amqptemplate;

import com.aliyun.aliyundemo.rabbitmq.chuxue.config.RabbitMQConfig;
import com.aliyun.aliyundemo.rabbitmq.chuxue.model.QueueUser;
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
    public void receiveDirectMsg1(QueueUser user) {
        log.info("我是1号direct-queue,监听到消息:{}", new Gson().toJson(user));
    }

    /**
     * receiveDirectMsg1和receiveDirectMsg2监控了同一个队列,此时接收消息是轮训的方式,每个消费者获得唯一的消息
     * @param user
     */
    @RabbitListener(queues = RabbitMQConfig.DIRECT_QUEUE_NAME)
    public void receiveDirectMsg2(QueueUser user) {
        log.info("我是2号direct-queue,监听到消息:{}", new Gson().toJson(user));
    }


    /**
     * topic
     */
    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE_NAME1)
    public void receiveTopic1Msg(QueueUser user) {
        log.info("我是topic-queue1,监听到消息:{}", new Gson().toJson(user));
    }

    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE_NAME2)
    public void receiveTopic2Msg(QueueUser user) {
        log.info("我是topic-queue2,监听到消息:{}", new Gson().toJson(user));
    }


    /**
     * fanout
     */

    @RabbitListener(queues = RabbitMQConfig.FANOUT_QUEUE_NAME1)
    public void receiveFanout1Msg(QueueUser user){
        log.info("我是fanout-queue1,监听到消息:{}", new Gson().toJson(user));
    }

    @RabbitListener(queues = RabbitMQConfig.FANOUT_QUEUE_NAME2)
    public void receiveFanout2Msg(QueueUser user){
        log.info("我是fanout-queue2,监听到消息:{}", new Gson().toJson(user));
    }

}
