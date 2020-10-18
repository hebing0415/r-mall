package com.robot.home.controller;

import com.robot.api.request.AddressRequest;
import com.robot.api.response.Message;
import com.robot.api.util.LocalUserUtil;
import com.robot.home.annotation.ControllerLog;
import com.robot.member.provider.MemberProvider;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/address")
@CrossOrigin
public class AddressController {

    private static Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Reference(version = "1.0.0", check = false)
    private MemberProvider memberProvider;


    @RequestMapping("/add")
    @ResponseBody
    @ControllerLog(description = "添加地址")
    public Message add(@RequestBody AddressRequest request) {
        String uid = LocalUserUtil.getUid();
        request.setUid(uid);
        return memberProvider.addAddress(request);
    }

    @RequestMapping("/find")
    @ResponseBody
    @ControllerLog(description = "查询地址")
    public Message find() {
        String uid = LocalUserUtil.getUid();
        return memberProvider.findAddress(null, uid);
    }

    @RequestMapping("/findById")
    @ResponseBody
    @ControllerLog(description = "查询地址")
    public Message findById(@RequestBody AddressRequest request) {
        String uid = LocalUserUtil.getUid();
        return memberProvider.findAddress(request, uid);
    }


    @RequestMapping("/update")
    @ResponseBody
    @ControllerLog(description = "更新地址")
    public Message update(@RequestBody AddressRequest request) {
        String uid = LocalUserUtil.getUid();
        return memberProvider.updateAddress(request, uid);
    }

    @RequestMapping("/delete")
    @ResponseBody
    @ControllerLog(description = "删除地址")
    public Message delete(@RequestBody AddressRequest request) {
        String uid = LocalUserUtil.getUid();
        return memberProvider.deleteAddress(request, uid);
    }
}
