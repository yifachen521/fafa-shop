package com.song.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author 炜哥哥
 * @date 2020/3/20 20:36
 */
@Component
public class MailUtil {
    //发送邮件 的方法封装
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;
    @Value("${activeUrl}")
    private String activeUrl;

    public void SenMailByTemplate(String receiveAccount,String uuid) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper mailMessage = new MimeMessageHelper(message,true);
        mailMessage.setSubject("澳门线上赌场");

        //读取模板内容
        Context context = new Context();
        context.setVariable("username",receiveAccount.substring(0,receiveAccount.lastIndexOf("@")));
        context.setVariable("uuid",activeUrl+uuid);
        //放入邮件模板的名称
        String info = templateEngine.process("message", context);

        mailMessage.setText(info,true);

        mailMessage.setFrom("2250680284@qq.com");
        mailMessage.setTo(receiveAccount);

        javaMailSender.send(message);
    }

}
