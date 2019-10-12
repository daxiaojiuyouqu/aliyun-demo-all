package com.aliyun.aliyundemo.rabbitmq.shizhan.config;

import com.aliyun.aliyundemo.rabbitmq.shizhan.service.UserOrderListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
@Slf4j
public class RabbitMQConfig {

    @Autowired
    private Environment env;

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    /**
     * RabbitMQ监听器listener 的容器工厂
     * 单一消费者
     *
     * @return
     */
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        factory.setPrefetchCount(1);
        factory.setTxSize(1);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }

    /**
     * RabbitMQ监听器listener 的容器工厂
     * 多个消费者
     *
     * @return
     */
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory, connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.concurrency", Integer.class));
        factory.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.max-concurrency", Integer.class));
        factory.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.simple.prefetch", Integer.class));
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        //支持消息发送成功确认
        connectionFactory.setPublisherConfirms(true);
        //支持消息发送失败回调
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            //消息发送成功,才会确认
            log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
        });
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            //消息发送失败才会回调,如果失败这里可以处理一些逻辑
            log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message);
        });
        return rabbitTemplate;
    }

    @Autowired
    private UserOrderListener userOrderListener;

    @Bean
    public SimpleMessageListenerContainer listenerContainer(@Qualifier(value = "userOrderQueue") Queue userOrderQueue) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //并发配置
        container.setConcurrentConsumers(Objects.requireNonNull(env.getProperty("spring.rabbitmq.listener.simple.concurrency",Integer.class)));
        container.setMaxConcurrentConsumers(Objects.requireNonNull(env.getProperty("spring.rabbitmq.listener.simple.max-concurrency",Integer.class)));
        container.setPrefetchCount(Objects.requireNonNull(env.getProperty("spring.rabbitmq.listener.simple.prefetch",Integer.class)));
        //接收消息确认机制
        container.setQueues(userOrderQueue);
        container.setMessageListener(userOrderListener);
        return container;

    }

    @Bean(name = "userOrderQueue")
    public Queue userOrderQueue() {
        return new Queue(Objects.requireNonNull(env.getProperty("user.order.queue.name")), true);
    }

    @Bean
    public TopicExchange userOrderExchange() {
        return new TopicExchange(Objects.requireNonNull(env.getProperty("user.order.exchange.name")), true, false);
    }

    @Bean
    public Binding userOrderBinding() {
        return BindingBuilder.bind(userOrderQueue()).to(userOrderExchange()).with(Objects.requireNonNull(env.getProperty("user.order.routing.key.name")));
    }

}
