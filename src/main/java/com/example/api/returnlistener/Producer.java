package com.example.api.returnlistener;


import com.example.api.conf.ConnectionConfig;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ReturnListener;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * <p>ReturnListener主要用于没有路由到指定队列的消息</p>
 * @author dinghy
 * @date 2019/7/9 17:48
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionConfig config = new ConnectionConfig();
        Channel channel = config.getChannel();

        String exchangeName = "test.return,exchange";
        String routingKey = "return.save";
        String routingErrorKey="return.error";
        String msg = "hello mq return listener";
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("-------handleReturn--------");
                System.out.println("replyCode: "+replyCode);
                System.out.println("replyText: "+replyText);
                System.out.println("exchange: "+exchange);
                System.out.println("routingKey: "+routingKey);
                System.out.println("properties: "+properties);
                System.out.println("msg: "+new String(body,"utf-8"));
            }
        });
        channel.basicPublish(exchangeName, routingKey, true, false, null, msg.getBytes());
        channel.basicPublish(exchangeName, routingErrorKey, true, false, null, msg.getBytes());

    }
}
