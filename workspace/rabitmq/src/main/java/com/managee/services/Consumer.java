package com.managee.services;

import com.rabbitmq.client.*;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();
        String exchangeName = "hello-exchange";
        channel.exchangeDeclare(exchangeName, "direct", true);

        String queueName = channel.queueDeclare().getQueue();
        System.out.println(queueName);
        String routingKey = "hola";
        channel.queueBind(queueName, exchangeName, routingKey);

        while(true) {
            boolean autoAck = false;
            String consumerTag = "";
            channel.basicConsume(queueName, autoAck, consumerTag, new DefaultConsumer(channel) {
                @SneakyThrows
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String routingKey = envelope.getRoutingKey();
                    String contentType = properties.getContentType();
                    System.out.println("消费的路由键值：" + routingKey);
                    System.out.println("消费的内容类型：" + contentType);
                    long deliveryTag = envelope.getDeliveryTag();
                    channel.basicAck(deliveryTag, false);
//                    String s = new String(body, "UTF-8");
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    Map o = (Map) objectInputStream.readObject();
                    System.out.println("消费的消息体内容：" + o.toString());
                }
            });
        }
    }
}
