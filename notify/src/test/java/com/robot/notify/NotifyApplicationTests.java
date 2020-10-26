package com.robot.notify;

import com.robot.api.pojo.AliYunEmail;
import com.robot.api.util.RandomCodeUtil;
import com.robot.notify.service.AliYunSMSService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
class NotifyApplicationTests {

    @Autowired
    AliYunSMSService aliyunSMSService;

    @Test
    void contextLoads() {
        AliYunEmail aliYunEmail = new AliYunEmail();
        aliYunEmail.setAccountName("");
        aliYunEmail.setFromAlias("程序员小R");
        aliYunEmail.setAddressType(1);
        aliYunEmail.setTagName("");
        aliYunEmail.setReplyToAddress(true);
        List<String> list = new ArrayList<>();
        list.add("");
        aliYunEmail.setToAddress(list);
        //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
        //request.setToAddress(“邮箱1,邮箱2”);
        aliYunEmail.setSubject("验证码");
        String aliyunAccessKeyId = "";
        //阿里云accessKeySecret
        String aliyunAccessKeySecret = "";
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("code", RandomCodeUtil.createCode());
        aliYunEmail.setValueMap(valueMap);
        aliYunEmail.setTemplateHtml("mail");
        long startTime = System.currentTimeMillis();
        aliyunSMSService.sendEmail(aliYunEmail);
        System.out.println(System.currentTimeMillis() - startTime);
    }

}
