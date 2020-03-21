package com.song.task;

import DTO.TUserDTO;
import com.song.utils.RedisPackages;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 炜哥哥
 * @date 2020/3/20 15:39
 */
@Component
public class UserRedis {

    @Autowired
    private RedisPackages redisPackages;

    //监听user_login_info_queue队列中的消息  并消费
    @RabbitListener(queues = "user_login_info_queue")
    public void saveUserInfo(TUserDTO tUserDTO) {

        //zu组织key作为存入到redis数据库中的键
        String user_info = String.format("user_info:%s", tUserDTO.getPhone());
        redisPackages.setInfoAndSecond(user_info, tUserDTO, 60 * 30);

    }
}
