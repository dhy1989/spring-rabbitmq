package com.example.demo.topic;

import com.example.demo.conf.ConnectionConfig;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author dinghy
 * @date 2019/7/9 08:52
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionConfig config = new ConnectionConfig();
        Channel channel = config.getChannel();
        String routingKey1="user.save";
        String routingKey2="user.update";
        String routingKey3="user.delete.abc";
        String exchangeName="test.topic";
        String msg="hello mq topic exchange....";
        channel.basicPublish(exchangeName,routingKey1,null,msg.getBytes());
        channel.basicPublish(exchangeName,routingKey2,null,msg.getBytes());
        channel.basicPublish(exchangeName,routingKey3,null,msg.getBytes());
       // config.shutDown();
    }
}
