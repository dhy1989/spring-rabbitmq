package com.example.api.qos;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author dinghy
 * @date 2019/7/10 08:58
 */
public class MyConsumer extends DefaultConsumer {
private Channel channel;
    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public MyConsumer(Channel channel) {
        super(channel);
        this.channel=channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("---------------------handleDelivery-----------------------");
        System.out.println("consumerTag: "+consumerTag);
        System.out.println("envelope: "+envelope);
        System.out.println("properties: "+properties);
        System.out.println("body: "+new String(body));
        channel.basicAck(envelope.getDeliveryTag(),false);
    }
}
