package com.aliyun.aliyundemo.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String DIRECT_QUEUE_NAME = "direct_queue";
    public static final String TOPIC_QUEUE_NAME1 = "topic_queue1";
    public static final String TOPIC_QUEUE_NAME2 = "topic_queue2";
    public static final String TOPIC_EXCHANGE = "topic_exchange";
    public static final String FANOUT_EXCHANGE = "fanout_exchange";
    public static final String ROUTING_KEY1 = "yxs.msg";
    /**
     * 注意:'*'表示一个词,'#'表示零个或多个
     */
    public static final String ROUTING_KEY2 = "yxs.#";


    /************************direct模式声明的消息队列********************************/
    @Bean
    public Queue directQueue() {
        return new Queue(DIRECT_QUEUE_NAME, true);
    }


    /************************topic模式声明的消息队列和交换机,并将队列和交换机绑定,设置routing-key(路由键)********************************/
    @Bean
    public Queue topicQueue1() {
        return new Queue(TOPIC_QUEUE_NAME1, true);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(TOPIC_QUEUE_NAME2, true);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(ROUTING_KEY1);
    }

    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(ROUTING_KEY2);
    }


    /***********************fanout模式:就是我们熟悉的广播模式或者订阅模式，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息********************************/

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    /**
     * 这里还是使用topic声明的两个消息队列
     *
     * @return
     */
    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(fanoutExchange());
    }


}
