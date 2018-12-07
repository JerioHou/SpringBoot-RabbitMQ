package com.jerio.mq;

import com.jerio.config.RabbitmqConfig;
import com.jerio.domain.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;


/**
 * Created by Jerio on 2018/8/12
 */
@Component
public class Sender{
    @Autowired
    private  AmqpTemplate amqpTemplate;

    @Autowired
    @Qualifier("myRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    User user1 = new User("user1");
    User user2 = new User("user2");

    /**
     * 消息直接发送到queue中
     *
     * convertAndSend(String routingKey, Object object)
     *
     * 使用的是默认的exchange
     * 队列的名字 就是routingKey
     */
    public void sendByDirect(){
        System.out.println("Direct模式");
        System.out.println("生产者 发送消息  ");
        amqpTemplate.convertAndSend(RabbitmqConfig.QUEUE_1, user1);
        amqpTemplate.convertAndSend(RabbitmqConfig.QUEUE_2, user2);
    }

    /**
     * topicExchange 模式
     *
     * convertAndSend(String exchange, String routingKey, Object object)
     *
     *发送消息的时候要明确发到哪个 exchange, 以及对应routingKey
     *消息服务器会根据exchange+routingKey找到对应的queue
     *最终 消费者会从自己所监听的队里中取消息
     */
    public void sendByTopic1(){
        System.out.println("Topic模式");
        System.out.println("生产者 发送消息  ");
        amqpTemplate.convertAndSend(RabbitmqConfig.TOPIC_EXCHANGE,RabbitmqConfig.ROUTING_KEY1,user1);
    }

    /**
     * ROUTING_KEY2 = topic.#
     * 所以绑定在RabbitmqConfig.TOPIC_EXCHANGE的队列，只要routingKey以topic开头，就会接受到消息
     */
    public void sendByTopic2(){
        System.out.println("Topic模式");
        System.out.println("生产者 发送消息  ");
        amqpTemplate.convertAndSend(RabbitmqConfig.TOPIC_EXCHANGE,RabbitmqConfig.ROUTING_KEY2,user1);
    }

    /**
     * 广播模式，发送到所有绑定在FANOUT_EXCHANGE的队列
     * 与routingKey无关
     * 不过还是要使用 convertAndSend(String exchange, String routingKey, Object object)
     *
     */
    public void sendByFanout(){
        System.out.println("Fanout模式");
        System.out.println("生产者 发送消息  ");
        amqpTemplate.convertAndSend(RabbitmqConfig.FANOUT_EXCHANGE,"",user2);
        //routingKey的值 无影响
        amqpTemplate.convertAndSend(RabbitmqConfig.FANOUT_EXCHANGE,"key",user1);
    }

    public void sendDirectAck(){
        System.out.println("Direct模式");
        System.out.println("生产者 发送消息  ");
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitmqConfig.QUEUE_1, user1,correlationData);
    }
}
