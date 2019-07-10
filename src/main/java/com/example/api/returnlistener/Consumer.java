package com.example.api.returnlistener;

import com.example.api.conf.ConnectionConfig;
import com.example.api.consumer.MyConsumer;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author dinghy
 * @date 2019/7/9 17:59
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionConfig config = new ConnectionConfig();
        Channel channel = config.getChannel();
        String exchangeName = "test.return,exchange";
        String routingKey = "return.save";
        String queueName="return.queue";
        channel.exchangeDeclare(exchangeName,"topic");
        channel.queueDeclare(queueName,false,false,true,null);
        channel.queueBind(queueName,exchangeName,routingKey);
        channel.basicConsume(queueName,new MyConsumer(channel));
    }

}
