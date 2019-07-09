package com.example.api.fanout;

import com.example.api.conf.ConnectionConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author dinghy
 * @date 2019/7/9 10:48
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionConfig config = new ConnectionConfig();
        Channel channel = config.getChannel();
        String exchangeName="test.fanout";
        String routingKey="";
        String queueName="fanout.queue";
        channel.exchangeDeclare(exchangeName,"fanout");
        channel.queueDeclare(queueName,true,false,false,null);
        channel.queueBind(queueName,exchangeName,routingKey);
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName,true,consumer);
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            System.out.println(new String(delivery.getBody(),"utf-8"));
        }
    }
}