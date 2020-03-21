package com.song.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 炜哥哥
 * @date 2020/3/18 21:02
 */
@Controller
public class IndexController {

    @RequestMapping(path = {"","/index"})
    public String index(){

        return "index";
    }
}
