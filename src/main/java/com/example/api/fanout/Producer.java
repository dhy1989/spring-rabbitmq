package com.example.api.fanout;

import com.example.api.conf.ConnectionConfig;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author dinghy
 * @date 2019/7/9 10:44
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionConfig config = new ConnectionConfig();
        Channel channel = config.getChannel();
        String exchangeName="test.fanout";
        String routingKey="hello.mq";
        String msg="hello mq fanout exchange";
        channel.basicPublish(exchangeName,routingKey,null,msg.getBytes());
    }
}
