package com.song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import resoutBean.ResultBean;

/**
 * @author 炜哥哥
 * @date 2020/3/18 22:33
 */
@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/login")
    public ResultBean login(String phone,String password){
        String url=
                String.format("http://shop-user-service/checkLogin?phone=%s&password=%s",phone,password);
       return restTemplate.getForObject(url,ResultBean.class);
    }

}
