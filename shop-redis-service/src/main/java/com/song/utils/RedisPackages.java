package com.song.utils;

import execption.Myexecption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author 炜哥哥
 * @date 2020/3/19 20:30
 */
@Component
@Slf4j
public class RedisPackages {
    @Autowired
    private RedisTemplate redisTemplate;
    public void setInfoAndSecond(Object key,Object value,long timeOut){
        try {
            redisTemplate.opsForValue().set(key,value,timeOut, TimeUnit.SECONDS);

        } catch (Exception e) {
            //记录错误的信息
            log.error("[存入缓存失败] key={} value={}",key,value);
            throw new Myexecption("存入用户信息发生异常",433);
        }
    }

    //获取值并指定生存时间
    public Object getInfoAndSecond(Object key, long timeOut) {
        try {
            Object o = redisTemplate.opsForValue().get(key);
            //更新生存时间
            redisTemplate.expire(key,timeOut, TimeUnit.SECONDS);
            return o;
        } catch (Exception e) {
            log.error("[从redis缓存中取出数据失败] key={} ",key);
            throw new Myexecption("取出用户信息发生异常",432);
        }
    }




}
