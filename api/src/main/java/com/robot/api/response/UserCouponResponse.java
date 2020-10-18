package com.robot.api.response;

import lombok.Data;

@Data
public class UserCouponResponse {


    /**
     * 券id
     */
    private String couponId;


    /**
     * 券模板id
     */
    private Integer couponTplId;

    /**
     * 券名称
     */
    private String name;

    /**
     * 券类型
     */
    private Integer type;

    /**
     * 满减条件
     */
    private String condition;

    /**
     * 开始时间
     */
    private long startAt;

    /**
     * 结束时间
     */
    private long endAt;

    /**
     * 描述
     */
    private String description;

    /**
     * 不可用原因
     */
    private String reason;

    /**
     * 折扣券的金额
     */
    private Long value;

    /**
     * 折扣券优惠文案
     */
    private String valueDesc;

    /**
     * 单位元
     */
    private String unitDesc;

}
