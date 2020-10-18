package com.robot.order.provider.impl;

import com.alibaba.fastjson.JSONObject;
import com.robot.api.request.CreateOrderRequest;
import com.robot.api.request.OrderListRequest;
import com.robot.api.request.OrderSureRequest;
import com.robot.api.response.ErrorType;
import com.robot.api.response.Message;
import com.robot.api.util.wxpay.WXPayUtil;
import com.robot.order.provider.OrderProvider;
import com.robot.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service(version = "1.0.0", timeout = 5000)
@Slf4j
public class OrderProviderImpl implements OrderProvider {

    @Resource
    private OrderService orderService;

    public Message orderSure(OrderSureRequest request, String uid) {
        try {
            return orderService.orderSure(request, uid);
        } catch (Exception e) {
            log.error("orderSure error uid:{}", uid, e);
        }
        return null;
    }

    @Override
    public Message createOrder(CreateOrderRequest request, String uid) {
        try {

            return orderService.createOrder(request, uid);
        } catch (Exception e) {
            log.error("createOrder error uidd:{}", uid, e);
            return null;
        }
    }


    @Override
    public Message orderPay(String uid, String orderCode) {
        try {
            return orderService.orderPay(uid, orderCode);
        } catch (Exception e) {
            log.error("createOrder error uid:{}", uid, e);
            return null;
        }
    }

    @Override
    public Message findOrderList(String uid, OrderListRequest request) {
        try {
            return orderService.orderList(uid,request);
        }catch (Exception e){
            log.error("findOrderList error uid:{}", uid, e);
            return Message.error(ErrorType.ERROR);

        }
    }

    @Override
    public String payNotify(Map<String, String> params) {
        Map<String, String> return_data = new HashMap<String, String>();
        try {
            return orderService.payNotify(params);
        } catch (Exception e) {
            log.error("支付回调异常:{}", JSONObject.toJSONString(params), e);
            return_data.put("return_code", "FAIL");
            return_data.put("return_msg", "业务处理异常");
            try {
                return WXPayUtil.mapToXml(return_data);
            } catch (Exception e2) {
                log.error("支付回调转换map失败");
            }
        }
        return null;
    }

    @Override
    public Message orderRedDot(String uid) {
        try {
            return orderService.orderRedDot(uid);
        } catch (Exception e) {
            log.error("orderRedDot error uid:{}", uid, e);
            return Message.error(ErrorType.ERROR);
        }
    }
}
