package com.example.demo.direct;

import com.example.demo.conf.ConnectionConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

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
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName,true,consumer);
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            System.out.println(new String(delivery.getBody(),"utf-8"));
        }
    }
}
