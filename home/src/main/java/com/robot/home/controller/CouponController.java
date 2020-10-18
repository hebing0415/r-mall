package com.robot.home.controller;


import com.robot.api.request.CouponRequest;
import com.robot.api.response.ErrorType;
import com.robot.api.response.Message;
import com.robot.api.util.LocalUserUtil;
import com.robot.home.annotation.ControllerLog;
import com.robot.member.provider.MemberProvider;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/coupon")
@CrossOrigin
public class CouponController {


    @Reference(version = "1.0.0", check = false)
    private MemberProvider memberProvider;

    @RequestMapping("/sendCoupon")
    @ResponseBody
    @ControllerLog(description = "发送优惠券")
    public Message sendCoupon(@RequestBody CouponRequest couponRequest) {
        String uid = LocalUserUtil.getUid();
        return memberProvider.sendCoupon(uid, couponRequest.getTplId());
    }


    @RequestMapping("/findCoupon")
    @ResponseBody
    @ControllerLog(description = "查询优惠券")
    public Message findCoupon() {
        String uid = LocalUserUtil.getUid();
        return Message.success(memberProvider.findCoupon(uid), ErrorType.SUCCESS);
    }
}
