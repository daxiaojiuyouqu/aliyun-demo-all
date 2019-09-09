package com.aliyun.aliyundemo.rabbitmq.service;

import com.aliyun.aliyundemo.rabbitmq.config.RabbitMQConfig;
import com.aliyun.aliyundemo.rabbitmq.model.QueueUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProducerService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 直接将消息发送到消息队列
     */
    public void sendDirectMsg() {
        QueueUser user = new QueueUser(1L, "小明", 18, "小明学习不错!");
        this.amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_QUEUE_NAME, user);
        log.info("生产者已发送direct消息");
    }

    /**
     * 将消息发送到交换机,并设置路由键,符合路由策略的,消息才会进到消息队列
     */
    public void sendTopicMsg() {
        QueueUser user1 = new QueueUser(2L, "小王", 18, "小王喜欢玩CF!");
        QueueUser user2 = new QueueUser(3L, "小张", 19, "小张喜欢打LOL!");
        this.amqpTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, RabbitMQConfig.ROUTING_KEY1, user1);
        this.amqpTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, RabbitMQConfig.ROUTING_KEY2, user2);
        log.info("生产者已发送topic消息");
    }

    /**
     * 将消息发送到交换机,无需设置路由键,因为消息会发到绑定到该交换机上的所有队列
     */
    public void sendFanoutMsg() {
        QueueUser user = new QueueUser(4L, "吒儿", 3, "哪吒闹海");
        //这里发送消息时,第二个参数不需要添加,fanout模式是将消息发送到绑定到指定交换机上的所有队列,所以不需要指定路由键(routing-key)
        this.amqpTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE, "", user);
        log.info("生产者已发送fanout消息");
    }

}
