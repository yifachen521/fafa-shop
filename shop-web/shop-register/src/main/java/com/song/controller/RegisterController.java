package com.song.controller;

import com.song.ribbon.RibbonConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import resoutBean.ResultBean;

import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;

/**
 * @author 炜哥哥
 * @date 2020/3/20 18:20
 */
@RestController
public class RegisterController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //1.拿到用户注册的邮箱   给用户发送邮件
    //2.将用户的信息存入redis中
    //3.将用户的信息存入数据库
    @RequestMapping("/register/{email}/{password}")
    public ResultBean register(@PathVariable String email,@PathVariable String password) {
        //1.拿到用户注册的邮箱   调用邮箱服务给用户发送邮件 将邮箱 服务需要的参数发送过去
        //生成一个uuid作为用户激活链接  这个链接同时又作为redis数据库的键
        String uuid = UUID.randomUUID().toString();

        //调用邮件服务
        String url = String.format("http://shop-mail-service/email/send/%s/%s", email, uuid);
        ResultBean resultBean = restTemplate.getForObject(url, ResultBean.class);
        ResultBean forObject =null;
        if (resultBean.getResultCode() == 201) {//邮件发送成功

            //将用户的信息存入redis中 通过rabbitmq
            //封装键和值
            Properties properties = new Properties();
            //uuid作为键
            properties.setProperty("email",email);
            properties.setProperty("uuid",uuid);
            rabbitTemplate.convertAndSend("user_login_info_exchange", "user_register", properties);
            //调用注册服务 将用户注册到数据库
            String register_url = String.format("http://shop-user-service/register/%s/%s", email,password);
            forObject = restTemplate.getForObject(register_url, resultBean.getClass());
        }
        return forObject;
    }


}
