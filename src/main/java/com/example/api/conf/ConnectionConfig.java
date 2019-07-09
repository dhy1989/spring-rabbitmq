package com.example.api.conf;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author dinghy
 * @date 2019/7/8 17:50
 */
public class ConnectionConfig {
    private String host = "192.168.65.100";
    private int port = 5672;
    private String userName = "root";
    private String password = "123456";
    private String virtual = "/";
    private Channel channel;
    private Connection connection;

    public Channel getChannel() throws IOException, TimeoutException {
        this.channel = getConnection().createChannel();
        return channel;
    }

    public Connection getConnection() throws IOException, TimeoutException {
        this.connection = getConFactory().newConnection();
        return connection;
    }

    public ConnectionFactory getConFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setVirtualHost(virtual);
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(userName);
        factory.setPassword(password);
        return factory;
    }

    public void shutDown() {
        try {
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
