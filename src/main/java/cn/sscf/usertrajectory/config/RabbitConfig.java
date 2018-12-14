package cn.sscf.usertrajectory.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_VALUE1="user";
    public static final String QUEUE_VALUE1="wyd.test1";
    public static final String ROUTINGKEY_A="online-user-trace";

	//直连交换机
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE_VALUE1);
    }
	
	@Bean //创建队列
     public Queue queue() {
          return new Queue(QUEUE_VALUE1);
     }

    @Bean //绑定routkey
    public Binding binding() {

        return BindingBuilder.bind(queue()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_A);
    }


}
