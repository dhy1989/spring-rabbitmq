package com.example.api.confirm;

import com.example.api.conf.ConnectionConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author dinghy
 * @date 2019/7/9 17:09
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionConfig config = new ConnectionConfig();
        Channel channel = config.getChannel();
        //选择确认模式
        channel.confirmSelect();

        String exchangeName="test.confirm";
        String routingKey="confirm.save";
        String msg="hello rabbitMq send confirm";
        channel.basicPublish(exchangeName,routingKey,null,msg.getBytes());
        //添加监听器
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("----------------ack-------------");
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("----------------noAck-------------");
            }
        });
    }
}
