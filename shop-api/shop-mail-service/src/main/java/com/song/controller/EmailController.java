package com.song.controller;

import com.song.util.MailUtil;
import com.sun.org.apache.regexp.internal.RE;
import execption.Myexecption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import resoutBean.ResultBean;

import javax.mail.MessagingException;

/**
 * @author 炜哥哥
 * @date 2020/3/20 20:57
 */
@RestController
@Slf4j
public class EmailController {

    @Autowired
    private MailUtil mailUtil;
    @RequestMapping("/email/send/{email}/{uuid}")
    public ResultBean sendEmail(@PathVariable("email") String email
            , @PathVariable("uuid") String uuid)  {
        try {
            mailUtil.SenMailByTemplate(email,uuid);
        } catch (MessagingException e) {
            log.error("[邮件发送异常]当前用户邮箱{}",email);
           throw new Myexecption("email send error",407);
        }
    return new ResultBean(201,"email send success",null);
    }
}
