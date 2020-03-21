package com.song.service;

import DTO.TUserDTO;
import com.song.mapper.UserMapper;
import com.song.pojo.TUser;
import execption.Myexecption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author 炜哥哥
 * @date 2020/3/18 21:32
 */
@Component
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public TUserDTO findUserById(String phone,String password) {
        TUser tUser = new TUser();
        tUser.setPhone(phone);
        tUser.setPassword(password);
        TUser select = userMapper.selectOne(tUser);
        //判断
        if(select==null){
            log.error("[用户登录] 用户登录失败 用户手机->{},用户密码->{}",phone,password);
           //抛出异常 由消费者的异常处理类统一处理
           throw new Myexecption("登录失败，请检查你的用户名或密码",444);
        }
        //登录成功
        TUserDTO tUserDTO = new TUserDTO();
        BeanUtils.copyProperties(select,tUserDTO);//将select中的数据拷贝到tUserDTO中
        // 使用rabbitMQ将查到的用户存入redis数据库 指定交换机和路由键 --》将select信息发送到user_login_info_exchange交换机上
        rabbitTemplate.convertAndSend("user_login_info_exchange","user_login_info",tUserDTO);
        return tUserDTO;
    }

    //用户注册

    @Override
    public Integer registerUser(TUserDTO tUserDTO) {
        //添加注册状态 在未激活时 flag的属性应该是0
        tUserDTO.setFlag(0);
        //获取当前时间
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        tUserDTO.setCreateTime(timestamp);
        TUser tUser = new TUser();
        BeanUtils.copyProperties(tUserDTO,tUser);
        int i = userMapper.insertSelective(tUser);
        return i;
    }


    //激活账户
    @Override
    public Integer activeAccount(String s) {
        TUser tUser = new TUser();
        tUser.setFlag(1L);
        //设置修改的条件
        Example example = new Example(TUser.class);
        Example.Criteria criteria = example.createCriteria();
       //设置条件  根据什么来修改 传入如：email="15037752845@163.com"
        //UPDATE t_user SET flag = ? WHERE ( ( email = ? ) ) 即为执行的sql

        //email实际上也需要从请求的路径中获取到  这里就直接设置了
        criteria.andEqualTo("email","15037752845@163.com");
        //第一个参数是要修改的内容的 封装
        //第二个参数是修改条件的封装
        int i = userMapper.updateByExampleSelective(tUser, example);

        return i;
    }
}
