package song.exchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("user_login_info_exchange", true, false, null);
    }
    //声明队列

}
