package com.aliyun.aliyundemo.rabbitmq.chuxue.service.rabbittemplate;

import com.aliyun.aliyundemo.rabbitmq.chuxue.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class Consumer {

    @RabbitHandler
    @RabbitListener(queues = RabbitMQConfig.FANOUT_QUEUE_NAME3)
    public void receiveMsg(Message message, Channel channel) throws IOException {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("接收到的消息:{}", new String(message.getBody(), StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("接收消息失败");
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

}
