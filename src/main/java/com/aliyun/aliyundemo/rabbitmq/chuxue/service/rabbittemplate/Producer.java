package com.aliyun.aliyundemo.rabbitmq.chuxue.service.rabbittemplate;

import com.aliyun.aliyundemo.rabbitmq.chuxue.config.RabbitMQConfig;
import com.aliyun.aliyundemo.rabbitmq.chuxue.model.QueueUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@Slf4j
public class Producer implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(QueueUser user) {
        this.rabbitTemplate.setConfirmCallback(this);
        this.rabbitTemplate.setReturnCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        log.info("发送的消息:{}", user);
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE2, "", user, correlationData);
    }

    public void sendMsg(String str) {
        this.rabbitTemplate.setConfirmCallback(this);
        this.rabbitTemplate.setReturnCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        log.info("发送的消息:{}", str);
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE2, "", str, correlationData);
    }


    /**
     * 如果消息没有到exchange,则confirm回调,ack=false
     * 如果消息到达exchange,则confirm回调,ack=true
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("消息ID:{}", correlationData.getId());
        //根据ack处理不同情况,成功可以打印日志说明,失败可以重试等等...
        if (ack) {
            log.info("消息发送确认成功");
        } else {
            log.error("消息发送确认失败:{}", cause);
        }
    }

    /**
     * exchange到queue成功,则不回调return
     * exchange到queue失败,则回调return
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("return--message:" + new String(message.getBody(), StandardCharsets.UTF_8) + ",replyCode:" + replyCode
                + ",replyText:" + replyText + ",exchange:" + exchange + ",routingKey:" + routingKey);
    }
}
