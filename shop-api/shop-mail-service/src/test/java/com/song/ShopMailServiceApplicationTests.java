package com.song;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@SpringBootTest
class ShopMailServiceApplicationTests {

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;
    //简单的邮件测试
    @Test
    void contextLoads() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("测试邮件主题");
        mailMessage.setText("这是一次测试邮件服务；--》毛毛在干嘛呢，想我没有");
        mailMessage.setFrom("2250680284@qq.com");
        mailMessage.setTo("572015526@qq.com");
        javaMailSender.send(mailMessage);

    }

//带html邮件模板的邮件测试
    @Test
    public void sendMail1() throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper mailMessage = new MimeMessageHelper(message,true);
        mailMessage.setSubject("测试邮件主题");
        mailMessage.setText("点击一下内容，完成计划<a href='http://www.baidu.com'>http://www.baidu.com</a>",true);
        mailMessage.setFrom("214490523@qq.com");
        mailMessage.setTo("hzjavatestmail@sina.com");
        javaMailSender.send(message);
    }

//通过模板发送邮件
    @Test
    public void testSenMailByTemplate() throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper mailMessage = new MimeMessageHelper(message,true);
        mailMessage.setSubject("测试邮件主题");

        //读取模板内容
        Context context = new Context();
        context.setVariable("username","炜哥哥");
        //放入邮件模板的名称
        String info = templateEngine.process("message", context);

        mailMessage.setText(info,true);

        mailMessage.setFrom("2250680284@qq.com");
        mailMessage.setTo("15037752845@163.com");

        javaMailSender.send(message);
    }





}
