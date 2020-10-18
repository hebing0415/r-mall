package com.robot.api.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 预支付订单
 */

@Data
public class AdvanceOrder {

    //待支付信息
    private BigDecimal payAmount;

    //优惠券id
    private String couponId;

    //优惠券模板id
    private Integer couponTplId;

    //积分
    private int point;

    //地址id
    private Integer addressId;

    //用户购物车数据
    private List<CartEntry> cartEntries;

    //uid
    private String uid;


}
