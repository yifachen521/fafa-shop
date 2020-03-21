package com.song.service;

import DTO.TUserDTO;

/**
 * @author 炜哥哥
 * @date 2020/3/18 16:34
 */
public interface UserService {
    //用户登录  用户查询根据用户id
    TUserDTO findUserById(String phone,String password);

    //用户注册
    Integer registerUser(TUserDTO tUserDTO);


    //激活用户
    Integer activeAccount(String s);


}
