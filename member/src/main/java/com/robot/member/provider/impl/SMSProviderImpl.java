package com.robot.member.provider.impl;

import com.robot.api.enums.RedisKeyEnum;
import com.robot.api.middleware.BaseRedis;
import com.robot.api.response.ErrorType;
import com.robot.api.response.Message;
import com.robot.member.provider.SMSProvider;
import com.robot.member.service.RandomCodeService;
import com.robot.member.util.EmailUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;


@Service(version = "1.0.0", timeout = 5000)
public class SMSProviderImpl implements SMSProvider {

    @Resource
    private RandomCodeService randomCodeService;

    @Resource
    private BaseRedis baseRedis;

    @Value("${sms_times}")
    private int sms_times;

    @Value("${email_times}")
    private int email_times;

    @Value("${aliyunCode}")
    private String aliyunCode;

    public Message sendEmail(String phone,String address) {

        String times = baseRedis.get(RedisKeyEnum.EMAIL_TIMES_KEY.getKey());
        if (StringUtils.isNotBlank(times) && Integer.parseInt(times) > email_times) {
            return Message.error(ErrorType.EMAIL_TIMES_EXCEED);
        }
        String randCode = randomCodeService.createCode(phone);
//        if (EmailUtil.sendEmail(StaticUtil.aliyunAccessKeyId, StaticUtil.aliyunAccessKeySecret, address, randCode)) {
//            baseRedis.incr(RedisKeyEnum.EMAIL_TIMES_KEY.getKey());
//            Calendar cal = Calendar.getInstance();
//            cal.add(Calendar.DAY_OF_YEAR, 1);
//            cal.set(Calendar.HOUR_OF_DAY, 0);
//            cal.set(Calendar.SECOND, 0);
//            cal.set(Calendar.MINUTE, 0);
//            cal.set(Calendar.MILLISECOND, 0);
//            long tomorrow = cal.getTimeInMillis();
//            long today = System.currentTimeMillis();
//
//            /**
//             * todo 这里使用定时任务来做
//             */
//            if (tomorrow < today) {
//                baseRedis.decrBy(RedisKeyEnum.EMAIL_TIMES_KEY.getKey(), Integer.parseInt(times));
//            }
//            return Message.success(ErrorType.SUCCESS);
//        }
        return Message.error(ErrorType.SEND_EMAIL_ERROR);
    }

    @Override
    public Message sendSMS(String phone) {
        String times = baseRedis.get(RedisKeyEnum.SMS_TIMES_KEY.getKey());
        if (StringUtils.isNotBlank(times) && Integer.parseInt(times) > sms_times) {
            return Message.error(ErrorType.EMAIL_TIMES_EXCEED);
        }
        String randCode = randomCodeService.createCode(phone);
        String templateParam = " {\"code\":\"%s\"}";
        EmailUtil emailUtil=new EmailUtil();
        if (emailUtil.sendMsg(phone, aliyunCode, String.format(templateParam, randCode))) {
            baseRedis.incr(RedisKeyEnum.SMS_TIMES_KEY.getKey());
            return Message.success(ErrorType.SUCCESS);
        }
        return Message.error(ErrorType.SEND_EMAIL_ERROR);
    }
}
