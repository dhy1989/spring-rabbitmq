package com.example.api.qos;

import com.example.api.conf.ConnectionConfig;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author dinghy
 * @date 2019/7/10 09:03
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionConfig config = new ConnectionConfig();
        Channel channel = config.getChannel();
        String exchangeName="qos.exchange";
        String routingKey="qos.routing";
        String msg="hello  rabbitMq consumer";
        for (int i = 0; i < 5; i++) {
            channel.basicPublish(exchangeName,routingKey,null,msg.getBytes());
        }
    }
}
