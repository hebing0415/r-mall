package com.robot.api.enums;

/**
 * @author robot
 * @date 2019/12/11 15:08
 */
public enum RedisKeyEnum {
    PRODUCT_KEY("商品List", "PRODUCT_KEY:::%s"),
    PRODUCT_DETAIL_KEY("商品详情", "PRODUCT_DETAIL_KEY:::%s"),
    CAROUSEL_KEY("首页轮播图","CAROUSEL_KEY:::%s"),
    CODE_KEY("验证码","CODE_KEY:::%s"),
    EMAIL_TIMES_KEY("email发送次数","CODE_TIMES_KEY"),
    SMS_TIMES_KEY("短信发送的次数","SMS_TIMES_KEY"),
    CART_KEY("用户购物车","%s:::CART_KEY"),
    SELECT_ALL_KEY("用户购物车是否全选","%s:::SELECT_ALL_KEY"),
    SET_ADDRESS_KEY("用户地址更新","SET_ADDRESS_KEY:::%s"),
    COUPON_ID("优惠券ID","COUPON_ID:::%s"),
    ORDER_CODE_KEY("订单code","ORDER_CODE_KEY:::%s")
    ;
    private String describe;

    private String key;

    RedisKeyEnum(String describe, String key) {
        this.describe = describe;
        this.key = key;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
