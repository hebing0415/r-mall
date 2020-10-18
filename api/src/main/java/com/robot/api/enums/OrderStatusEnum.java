package com.robot.api.enums;

import com.robot.api.pojo.Order;
import com.robot.api.response.PersonCenterResponse;

import java.util.List;

/**
 * 订单状态
 */
public enum OrderStatusEnum {

    TO_BE_PAID(0, "待支付"),
    PAID(2, "已支付"),
    TO_BE_DELIVERED(3, "已发货"),
    CANCELING(4, "取消中"),
    CANCELLED(5, "已退款"),
    RECEIVED(6, "已收货");


    private Integer code;

    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static PersonCenterResponse orderNum(List<Order> orderList) {
        PersonCenterResponse personCenterResponse=new PersonCenterResponse();
        int tobePay = 0;
        int pay = 0;
        int tobeDelivered = 0;
        int received = 0;
        for (Order order : orderList) {
            if (order.getOrderStatus().equals(OrderStatusEnum.TO_BE_PAID.getCode())) {
                tobePay++;
            } else if (order.getOrderStatus().equals(OrderStatusEnum.PAID.getCode())) {
                pay++;
            } else if (order.getOrderStatus().equals(OrderStatusEnum.TO_BE_DELIVERED.getCode())) {
                tobeDelivered++;
            } else if (order.getOrderStatus().equals(OrderStatusEnum.RECEIVED.getCode())) {
                received++;
            }
        }
        personCenterResponse.setTobePay(tobePay);
        personCenterResponse.setPay(pay);
        personCenterResponse.setTobeDelivered(tobeDelivered);
        personCenterResponse.setReceived(received);
        return personCenterResponse;
    }

}
