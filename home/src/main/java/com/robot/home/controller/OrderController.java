package com.robot.home.controller;


import com.alibaba.fastjson.JSONObject;
import com.robot.api.request.CreateOrderRequest;
import com.robot.api.request.OrderListRequest;
import com.robot.api.request.OrderPayRequest;
import com.robot.api.request.OrderSureRequest;
import com.robot.api.response.Message;
import com.robot.api.util.LocalUserUtil;
import com.robot.api.util.wxpay.WXPayUtil;
import com.robot.home.annotation.ControllerLog;
import com.robot.order.provider.OrderProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/order")
@CrossOrigin
@Slf4j
public class OrderController {

    @Reference(version = "1.0.0", check = false)
    private OrderProvider orderProvider;

    @RequestMapping("/orderSure")
    @ResponseBody
    @ControllerLog(description = "确认订单")
    public Message orderSure(@RequestBody OrderSureRequest request) {
        String uid = LocalUserUtil.getUid();
        return orderProvider.orderSure(request, uid);
    }

    @RequestMapping("/createOrder")
    @ResponseBody
    @ControllerLog(description = "创建订单")
    public Message createOrder(@RequestBody CreateOrderRequest request) {
        String uid = LocalUserUtil.getUid();
        return orderProvider.createOrder(request, uid);
    }

    @RequestMapping("/findOrderList")
    @ResponseBody
    @ControllerLog(description = "订单列表")
    public Message findOrderList(@RequestBody OrderListRequest request) {
        String uid = LocalUserUtil.getUid();
        return orderProvider.findOrderList(uid, request);
    }


    @RequestMapping("/orderPay")
    @ResponseBody
    @ControllerLog(description = "订单支付")
    public Message orderPay(@RequestBody OrderPayRequest orderPayRequest) {
        String uid = LocalUserUtil.getUid();
        return orderProvider.orderPay(uid, orderPayRequest.getOrderCode());
    }

    /**
     * 支付回调
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/wxPayNotify", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    @ControllerLog(description = "支付回调")
    public String notifyWeiXinPay(HttpServletRequest request) {
        log.info("支付回调");
        InputStream inStream = null;
        ByteArrayOutputStream outSteam = null;
        Map<String, String> params = new HashMap<>();
        try {
            inStream = request.getInputStream();
            outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            String result = new String(outSteam.toByteArray(), StandardCharsets.UTF_8);
            params = WXPayUtil.xmlToMap(result);
            log.info("支付回调参数:{}", JSONObject.toJSONString(params));
        } catch (Exception e) {
            log.error("支付回调异常", e);
        } finally {
            try {
                assert outSteam != null;
                outSteam.close();
            } catch (IOException e) {
                log.error("关闭OutputStream流失败", e);
            }
            try {
                inStream.close();
            } catch (IOException e) {
                log.error("关闭InputStream流异常", e);
            }
        }
        return orderProvider.payNotify(params);
    }
}
