package com.robot.order.service;

import com.robot.api.request.CreateOrderRequest;
import com.robot.api.pojo.OrderAndLine;
import com.robot.api.request.OrderListRequest;
import com.robot.api.request.OrderSureRequest;
import com.robot.api.response.Message;

import java.util.Map;

/**
 * @author robot
 * @date 2019/12/9 14:33
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param uid
     * @return
     */
    Message createOrder(CreateOrderRequest request, String uid) throws Exception;


    /**
     * 订单支付
     *
     * @param uid
     * @return
     */
    Message orderPay(String uid, String orderPay) throws Exception;

    /**
     * 订单确认
     *
     * @param uid
     * @return
     */
    Message orderSure(OrderSureRequest request, String uid) throws Exception;

    /**
     * 插入订单
     *
     * @param orderAndLine
     * @throws Exception
     */
    void insertOrder(OrderAndLine orderAndLine, Integer status) throws Exception;


    /**
     * 支付回调
     *
     * @param params
     * @return
     */
    String payNotify(Map<String, String> params) throws Exception;

    /**
     * 个人中心查询订单，红点
     *
     * @param uid
     * @return
     */
    Message orderRedDot(String uid);

    /**
     * 查询订单列表
     * @param uid
     * @param request
     * @return
     */
    Message orderList(String uid, OrderListRequest request);

    /**
     * 定时任务查询超时未支付的订单
     */
    void deleteOrder();
}
