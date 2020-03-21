package com.song.task;

import com.song.exchange.UserExchange;
import com.song.utils.RedisPackages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author 炜哥哥
 * @date 2020/3/20 21:41
 */
@Component
@Slf4j
public class Register {
    @Autowired
    private RedisPackages redisPackages;


    @RabbitListener(queues = "user_register_queue")
    public void registerInfoSave(Properties properties){
       String email = (String) properties.get("email");
        String uuid = (String) properties.get("uuid");
        redisPackages.setInfoAndSecond(uuid,email,60*30);


    }

}
