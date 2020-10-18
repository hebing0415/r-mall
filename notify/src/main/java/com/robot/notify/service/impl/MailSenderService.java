package com.robot.notify.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author robot
 * @date 2020/2/19 17:26
 */
public class MailSenderService {

    private static final Logger logger = LoggerFactory.getLogger(MailSenderService.class);

    private static ExecutorService executor = new ThreadPoolExecutor(10, 10, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue(10));

    public static Session getSession(String address, String password) {
        final Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        //此处填写SMTP服务器
        properties.put("mail.smtp.host", "smtp.qq.com");
        //端口号，QQ邮箱端口587
        properties.put("mail.smtp.port", "587");
        // 此处填写，写信人的账号
        properties.put("mail.user", address);
        // 此处填写16位STMP口令
        properties.put("mail.password", password);
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = properties.getProperty("mail.user");
                String password = properties.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        return Session.getInstance(properties, authenticator);
    }

    public void sendMail(final String recipient, final String subject, final String content, final String qq) {
//        executor.execute(new Runnable() {
//            public void run() {
        try {
            String address = null;
            String password = null;
            // 创建邮件消息
            MimeMessage message = new MimeMessage(getSession(address, password));
            // 设置发件人
            InternetAddress form = new InternetAddress(address);
            message.setFrom(form);
            // 设置收件人的邮箱
            InternetAddress to = new InternetAddress(qq);
            message.setRecipient(MimeMessage.RecipientType.TO, to);
            // 设置邮件标题
            message.setSubject("r-mall验证码");
            // 设置邮件的内容体
            message.setContent("【小R提醒您】,本次操作的验证码为[" + (int) ((Math.random() * 9 + 1) * 1000) + "];你当前正在进行[商城登录],5分钟内有效,请勿向任何人透露", "text/html;charset=UTF-8");

            // 最后当然就是发送邮件啦
            Transport.send(message);
        } catch (Exception e) {
            logger.error("邮件发送失败：", e);
        }
    }
}
