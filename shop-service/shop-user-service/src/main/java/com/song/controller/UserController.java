package com.song.controller;

import DTO.TUserDTO;
import com.song.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import resoutBean.ResultBean;

/**
 * @author 炜哥哥
 * @date 2020/3/19 10:06
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/checkLogin")
    public ResultBean login(String phone, String password) {
        TUserDTO userById = userService.findUserById(phone, password);
        return new ResultBean(200, "success", userById);
    }

    //用户注册
    @RequestMapping("/register/{email}/{password}")
    public ResultBean register(@PathVariable String email,
                               @PathVariable String password) {
        TUserDTO tUserDTO = new TUserDTO();
        tUserDTO.setEmail(email);
        tUserDTO.setPassword(password);
        Integer integer = userService.registerUser(tUserDTO);
        if (integer == 1) {
            return new ResultBean(200, "success", null);
        }
        return null;
    }

    //激活注册的状态
    @RequestMapping("/active/account/{uuid}")
    public ResultBean activateAccount(@PathVariable String uuid) {
        //根据uuid去redis去查该用户的信息  如果有  就激活
        String s = (String) redisTemplate.opsForValue().get(uuid);
        if (s != null) {
            //激活账号
            userService.activeAccount(s);
            log.info("[用户通过邮箱激活成功]");
            return new ResultBean(200, "激活成功，现在可以使用邮箱登录了", null);
        } else {
            log.error("[用户通过邮箱激活失败]");
            return new ResultBean(406, "激活失败，未查询到需要激活的账号", null);
        }
    }

}
