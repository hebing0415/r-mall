package com.robot.api.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderSureRequest implements Serializable {


    private Integer addressId;

    private String couponId;

}
