package com.song.exchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 炜哥哥      -----------!!!!!!!这里应该把 配置文件抽取到shop-rabbitmq里边  但是不成功
 * 报listener.BlockingQueueConsumer$DeclarationException: Failed to declare queue(s)
 * @date 2020/3/19 19:42
 */
@Configuration
public class UserExchange {

    //声明交换机
    @Bean("TOPIC_EXCHANGE")
    public TopicExchange TOPIC_EXCHANGE() {
        return new TopicExchange("user_login_info_exchange", true, false, null);
    }
    //声明队列
    @Bean("USER_LOGIN_QUEUE")
    public Queue USER_LOGIN_QUEUE() {
        return new Queue("user_login_info_queue");
    }

    @Bean("USER_REGISTER_QUEUE")
    public Queue USER_REGISTER_QUEUE() {
        return new Queue("user_register_queue");
    }
    //将队列绑定到交换机
    @Bean
    public Binding userLoginInfoBinding(@Qualifier("USER_LOGIN_QUEUE") Queue queue,
                                        @Qualifier("TOPIC_EXCHANGE") TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("user_login_info");
    }
    @Bean
    public Binding userRegisterBinding(@Qualifier("USER_REGISTER_QUEUE") Queue queue,
                                        @Qualifier("TOPIC_EXCHANGE") TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("user_register");
    }


}
