package com.example.api.direct;

import com.example.api.conf.ConnectionConfig;
import com.example.api.consumer.MyConsumer;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author dinghy
 * @date 2019/7/8 18:04
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionConfig conf = new ConnectionConfig();
        Channel channel = conf.getChannel();
        String exchangeName = "test.direct";
        String routKey = "hello.direct.test";
        String queueName = "test.direct.queue";
        channel.exchangeDeclare(exchangeName, "direct");
        channel.queueDeclare(queueName,true,true,true,null);
        channel.queueBind(queueName,exchangeName,routKey);
        channel.basicConsume(queueName,true,new MyConsumer(channel));

    }
}
