package com.jerio.mq;

import com.jerio.config.RabbitmqConfig;
import com.jerio.domain.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Jerio on 2018/8/12
 */
@Component
public class Receiver {

    //可以同时监听多个队列
    @RabbitListener(queues = {RabbitmqConfig.QUEUE_1,RabbitmqConfig.QUEUE_2})
    public void process1(User user,Message message, Channel channel) throws Exception{
        System.out.println(" 收到消息: " + user);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
//        channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
//        channel.basicNack(message.getMessageProperties().getDeliveryTag(),true,true);
    }


    @RabbitListener(queues = RabbitmqConfig.TOPIC_QUEUE1)
    public void process2(User user) {
        System.out.println(" topic队列1 收到消息: " + user);
    }

    @RabbitListener(queues = RabbitmqConfig.TOPIC_QUEUE2)
    public void process3(User user) {
        System.out.println(" topic队列2 收到消息: " + user);
    }
}
