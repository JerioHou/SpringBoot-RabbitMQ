package com.jerio.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Jerio on 2018/8/12
 */
@Configuration
public class RabbitmqConfig {

    public static final String QUEUE_1 = "queue_1";
    public static final String QUEUE_2 = "queue_2";
    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";
    public static final String TOPIC_EXCHANGE = "topicExchage";
    public static final String FANOUT_EXCHANGE = "fanoutExchage";
    public static final String ROUTING_KEY1 = "topic.key1";
    public static final String ROUTING_KEY2 = "topic.#";



    /**
     * Direct模式
     * */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_1, true);
    }

    @Bean
    public Queue queue2() {
        return new Queue(QUEUE_2, true);
    }

    /**
     * Topic模式 交换机Exchange
     * */
    @Bean
    public Queue topicQueue1(){
        return new Queue(TOPIC_QUEUE1,true);
    }

    @Bean
    public Queue topicQueue2(){
        return new Queue(TOPIC_QUEUE2,true);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    /**
     * 消息发送到交换机，附带一个routing key
     *根据exchange 和routingKey找到消息具体发送到哪个队里
     *这里的绑定定义的就是 exchang+routingkey-->queue
     * @return
     */
    @Bean
    public Binding topicBanding1(){
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(ROUTING_KEY1);
    }
    @Bean
    public Binding topicBanding2(){
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(ROUTING_KEY2);
    }

    /**
     * FANOUT Exchange 广播模式
     * */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    /**
     * 发送到FanoutExchange的消息，会转发到所有与FanoutExchange绑定的queue,
     * 与routingKey无关
     */
    @Bean
    public Binding fanoutBinding1(){
        return BindingBuilder.bind(topicQueue1()).to(fanoutExchange());
    }
    @Bean
    public Binding fanoutBinding2(){
        return BindingBuilder.bind(topicQueue2()).to(fanoutExchange());
    }
}
