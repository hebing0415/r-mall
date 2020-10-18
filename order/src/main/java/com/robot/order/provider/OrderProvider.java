package com.robot.order.provider;

import com.robot.api.request.CreateOrderRequest;
import com.robot.api.request.OrderListRequest;
import com.robot.api.request.OrderSureRequest;
import com.robot.api.response.Message;

import java.util.Map;

public interface OrderProvider {

    /**
     * 订单确认
     * @param uid
     * @return
     */
    Message orderSure(OrderSureRequest request, String uid) ;

    /**
     * 创建订单
     * @param request
     * @param uid
     * @return
     */
    Message createOrder(CreateOrderRequest request,String uid);

    /**
     * 订单支付
     * @param uid
     * @return
     */
    Message orderPay( String uid,String orderCode);


    /**
     * 查询订单列表
     * @param uid
     * @param request
     * @return
     */
    Message findOrderList(String uid, OrderListRequest request);


    /**
     * 支付回调
     * @param params
     * @return
     */
    String payNotify( Map<String, String> params);

    /**
     *
     * @param uid
     * @return
     */
   Message orderRedDot(String uid);

}
