package com.robot.api.request;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateOrderRequest {

    private Integer addressId;

    private String couponId;

    private String remark;

    private BigDecimal payAmount;

}
