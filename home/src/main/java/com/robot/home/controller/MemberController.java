package com.robot.home.controller;

import com.robot.api.enums.LoginStatusEnum;
import com.robot.api.pojo.User;
import com.robot.api.request.LoginRequest;
import com.robot.api.response.ErrorType;
import com.robot.api.response.Message;
import com.robot.api.util.*;
import com.robot.home.annotation.ControllerLog;
import com.robot.member.provider.MemberProvider;
import com.robot.member.provider.SMSProvider;
import com.robot.member.service.MemberService;
import com.robot.notify.provider.AliYunSMSProvider;
import com.robot.order.provider.OrderProvider;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author robot
 * @date 2020/1/7 15:41
 */
@Controller
@RequestMapping("/home")
@CrossOrigin
public class MemberController {

    private static Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Reference(version = "1.0.0", check = false)
    private MemberProvider memberProvider;

    @Reference(version = "1.0.0", check = false)
    private SMSProvider smsProvider;

    @Reference(version = "1.0.0", check = false)
    private AliYunSMSProvider aliYunSMSProvider;

    @Reference(version = "1.0.0", check = false)
    private MemberService memberService;

    @Reference(version = "1.0.0", check = false)
    private OrderProvider orderProvider;


    @ResponseBody
    @RequestMapping("/pwdLogin")
    @ControllerLog(description = "密码登录")
    public Message pwdLogin(HttpServletResponse response, @RequestBody LoginRequest loginRequest) throws Exception {
        String uid = loginRequest.getUid();
        String passWord = loginRequest.getPassWord();
        //生成token
        User user = memberProvider.findMemberByUid(uid);
        if (user == null) {
            logger.info("-----uid:{} 账号不存在，去注册 ------", uid);
            return Message.error(ErrorType.USER_NOT_EXIT);
        }
        //判断用户名和密码是否正确 ,这里采用的是RSA非对称加密
        if (!RSAUtils.verifyPwd(passWord, user.getPassword())) {
            return Message.error(ErrorType.PASSWORD_ERROR);
        }
        //登录成功 生成token
        Message message = JWTUtil.createJWT(uid, null);
        logger.info("-----uid:{},登录成功----- token:{}", uid, message.getData());
        user.setUserStatus(LoginStatusEnum.LOGIN_IN.getCode());
        //更新用户状态
        memberProvider.updateLoginUser(user);
        // 将token放在响应头
        response.setHeader(StaticUtil.AUTH_HEADER_KEY, StaticUtil.TOKEN_PREFIX + message.getData());
        return Message.success(message.getData());
    }

    /**
     * 验证码登录
     *
     * @param response
     * @param loginRequest
     * @return
     */

    @ResponseBody
    @RequestMapping("/userLogin")
    @ControllerLog(description = "用户登录")
    public Message userLogin(HttpServletResponse response, @RequestBody LoginRequest loginRequest) {
        String phone = loginRequest.getPhone();
        String code = loginRequest.getCode();
        String email = loginRequest.getEmail();
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(code)) {
            return Message.error(ErrorType.LOGIN_MESSAGE);
        }
        try {
            User user = memberProvider.findMember(phone, null);
            if (user == null) {
                logger.info("-----phone:{} 账号不存在，去注册 ------", phone);
                user.setPhone(phone);
                user.setEmail(email);
                memberProvider.userRegister(user);
            }
//            if (!memberProvider.verify(phone, code)) {
//                logger.info("-----phone:{},登陆失败----- 验证码错误", phone);
//                return Message.error(ErrorType.CODE_ERROR);
//            }
            //生成token
            Message message = JWTUtil.createJWT(phone, null);
            logger.info("-----phone:{},登录成功----- token:{}", phone, message.getData());
            user.setUserStatus(LoginStatusEnum.LOGIN_IN.getCode());
            //更新用户状态
            memberProvider.updateLoginUser(user);
            // 将token放在响应头
            response.setHeader(StaticUtil.AUTH_HEADER_KEY, StaticUtil.TOKEN_PREFIX + message.getData());
            return Message.success(message.getData());
        } catch (Exception e) {
            logger.error("MemberController userLogin error phone : {} ", phone, e);
            return Message.error(ErrorType.ERROR);
        }
    }

    /**
     * 获取验证码
     *
     * @param request
     * @param response
     * @return
     */

    @ResponseBody
    @RequestMapping("/getCode")
    @ControllerLog(description = "获取验证码")
    public Message getCode(HttpServletRequest request, HttpServletRequest response) {
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        if (StringUtils.isBlank(phone) || !AccountValidatorUtil.isMobile(phone)) {
            return Message.error(ErrorType.PHONE_ERROR);
        }
        if (StringUtils.isBlank(email) || !AccountValidatorUtil.isEmail(email)) {
            return Message.error(ErrorType.EMAIL_ERROR);
        }
        Message message = smsProvider.sendSMS(phone);
        if (StringUtils.equals(ErrorType.SUCCESS.getErrorCode(), message.getCode())) {
            return smsProvider.sendEmail(phone, email);
        } else {
            return message;
        }
    }

    @ResponseBody
    @RequestMapping("/getUserInfo")
    public Message getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        String uid = LocalUserUtil.getUid();
        return Message.success(ErrorType.SUCCESS);
    }

    @ResponseBody
    @RequestMapping("/personCenter")
    @ControllerLog(description = "个人中心")
    public Message personCenter(HttpServletRequest request, HttpServletResponse response) {
        String uid = LocalUserUtil.getUid();
        return Message.success(orderProvider.orderRedDot(uid));
    }


}
