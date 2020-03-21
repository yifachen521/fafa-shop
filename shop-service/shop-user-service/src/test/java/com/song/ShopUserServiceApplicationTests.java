package com.song;

import com.song.mapper.UserMapper;
import com.song.pojo.TUser;
import com.song.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import tk.mybatis.mapper.entity.Example;

@SpringBootTest
class ShopUserServiceApplicationTests {
   @Autowired
   private UserMapper userMapper;
    @Test
    void contextLoads() {
        TUser tUser = new TUser();
        tUser.setFlag(1L);



        Example example = new Example(TUser.class);
        Example.Criteria criteria = example.createCriteria();
        //criteria.andEqualTo("15037752845@163.com");

        //criteria.andCondition("15037752845@163.com");
        criteria.andEqualTo("email","15037752845@163.com");
        //第一个参数是要修改的内容的 封装
        //第二个参数是修改条件的封装
        int i = userMapper.updateByExampleSelective(tUser, example);
    }

}
