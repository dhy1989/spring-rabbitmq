package com.example.api.qos;

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
        String exchangeName="qos.exchange";
        String routingKey="qos.routing";
        String queueName="qos.queue";
        channel.exchangeDeclare(exchangeName,"topic");
        //arguments可以甚至死信队列参数
        channel.queueDeclare(queueName,false,false,true,null);
        channel.queueBind(queueName,exchangeName,routingKey);
        channel.basicQos(1);
        channel.basicConsume(queueName,false,new MyConsumer(channel));
    }
}
