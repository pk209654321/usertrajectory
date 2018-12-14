package cn.sscf.usertrajectory.send;



import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

import cn.sscf.usertrajectory.config.RabbitConfig;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class RabbitMqRPCClient {

    private Connection connection;
    private Channel channel;
    private String replyQueueName;

    public RabbitMqRPCClient() throws IOException, TimeoutException {

        //建立一个连接和一个通道，并为回调声明一个唯一的'回调'队列
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("121.196.192.154");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("123456");
        factory.setVirtualHost("/");
        connection = factory.newConnection();
        channel = connection.createChannel();
        //定义一个临时变量的接受队列名
        replyQueueName = channel.queueDeclare().getQueue();
    }

    //发送RPC请求
    public String call(String message) throws IOException, InterruptedException {
        //生成一个唯一的字符串作为回调队列的编号
        String corrId = UUID.randomUUID().toString();
        //发送请求消息，消息使用了两个属性：replyto和correlationId
        //服务端根据replyto返回结果，客户端根据correlationId判断响应是不是给自己的
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().correlationId(corrId).replyTo(replyQueueName).build();

        //发布一个消息，requestQueueName路由规则
        channel.basicPublish(RabbitConfig.EXCHANGE_VALUE1, RabbitConfig.ROUTINGKEY_A, props, message.getBytes("UTF-8"));

        //由于我们的消费者交易处理是在单独的线程中进行的，因此我们需要在响应到达之前暂停主线程。
        //这里我们创建的 容量为1的阻塞队列ArrayBlockingQueue，因为我们只需要等待一个响应。
        final BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);

        // String basicConsume(String queue, boolean autoAck, Consumer callback)
        channel.basicConsume(replyQueueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //检查它的correlationId是否是我们所要找的那个
                if (properties.getCorrelationId().equals(corrId)) {
                    //如果是，则响应BlockingQueue
                    response.offer(new String(body, "UTF-8"));
                }
            }
        });

        return response.take();
    }

    //发送RPC请求
    public String call(String requestQueueName, String message) throws IOException, InterruptedException {
        replyQueueName = channel.queueDeclare().getQueue();
        //生成一个唯一的字符串作为回调队列的编号
        String corrId = UUID.randomUUID().toString();
        //发送请求消息，消息使用了两个属性：replyto和correlationId
        //服务端根据replyto返回结果，客户端根据correlationId判断响应是不是给自己的
        //        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().correlationId(corrId).replyTo(replyQueueName).build();
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().correlationId(corrId).replyTo(replyQueueName).deliveryMode(2).contentType("application/json").build();
        //        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().correlationId(corrId).replyTo(replyQueueName).deliveryMode(2).build();

        //发布一个消息，requestQueueName路由规则
        channel.basicPublish("account", requestQueueName, props, message.getBytes("UTF-8"));

        //由于我们的消费者交易处理是在单独的线程中进行的，因此我们需要在响应到达之前暂停主线程。
        //这里我们创建的 容量为1的阻塞队列ArrayBlockingQueue，因为我们只需要等待一个响应。
        final BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);

        // String basicConsume(String queue, boolean autoAck, Consumer callback)
        channel.basicConsume(replyQueueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //检查它的correlationId是否是我们所要找的那个
                if (properties.getCorrelationId().equals(corrId)) {
                    //如果是，则响应BlockingQueue
                    response.offer(new String(body, "UTF-8"));
                }
            }
        });

        return response.take();
    }

    public void close() throws IOException {
        connection.close();
    }

    public static void main(String[] argv) {
        /*RPCClient rpc = null;
        String response = null;
        try {
            rpc = new RPCClient();

            response = rpc.call(ONLINE_ACCOUNT_MODIFY, "{\"api\":\"account-modify\",\"version\":1,\"timestamp\":1535970845908,\"data\":{\"nick_name\":\"11\",\"mobile\":\"dGe1odaGLXafLtq2E0S0/A==\",\"business_id\":0}}");
            System.out.println(response);
            response = rpc.call(ONLINE_ACCOUNT_RELATION_MODIFY, "{\"api\":\"account-relation-modify\",\"version\":1,\"timestamp\":1535970845908,\"data\":{\"user_id\":[\"57253\"],\"business_id\":\"1111\"}}\r\n" + "");
            System.out.println(response);
            response = rpc.call(ONLINE_ACCOUNT_QUERY, "{\"api\":\"account-query\",\"version\":1,\"timestamp\":1535970845908,\"data\":{\"business_id\":56982}}");
            System.out.println(response);
        } catch (IOException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (rpc != null) {
                try {
                    rpc.close();
                } catch (IOException _ignore) {
                }
            }
        }*/
    }
}