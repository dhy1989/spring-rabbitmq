package com.example.api.consumer;

import com.example.api.conf.ConnectionConfig;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * <p>自定义Consumer,消费消息{@link MyConsumer} /p>
 * @author dinghy
 * @date 2019/7/10 09:14
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionConfig config = new ConnectionConfig();
        Channel channel = config.getChannel();
        String exchangeName="consumer.exchange";
        String routingKey="consumer.routing";
        String queueName="consumer.queue";
        channel.exchangeDeclare(exchangeName,"topic");
        channel.queueDeclare(queueName,false,false,true,null);
        channel.queueBind(queueName,exchangeName,routingKey);
        channel.basicConsume(queueName,new MyConsumer(channel));
    }
}
