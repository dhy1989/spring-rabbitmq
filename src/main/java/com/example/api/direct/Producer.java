package com.example.api.direct;

import com.example.api.conf.ConnectionConfig;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author dinghy
 * @date 2019/7/8 17:49
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionConfig conf = new ConnectionConfig();
        Channel channel = conf.getChannel();
        String exchangeName="test.direct";
        String routKey="hello.direct";
        String message="hello mq";
        for (int i = 0; i < 5; i++) {
            channel.basicPublish(exchangeName,routKey,null,message.getBytes());
        }
    }
}
