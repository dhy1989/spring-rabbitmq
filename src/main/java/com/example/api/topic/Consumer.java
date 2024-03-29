package com.example.api.topic;

import com.example.api.conf.ConnectionConfig;
import com.example.api.consumer.MyConsumer;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author dinghy
 * @date 2019/7/9 09:12
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionConfig config = new ConnectionConfig();
        Channel channel = config.getChannel();
        String exchangeName = "test.topic";
        String routingKey = "user.#";
        String queueName = "queue.topic";
        channel.exchangeDeclare(exchangeName, "topic",false,false,false,null);
        channel.queueDeclare(queueName, true, false, true, null);
        channel.queueBind(queueName, exchangeName, routingKey);
        channel.basicConsume(queueName, true, new MyConsumer(channel));
    }
}
