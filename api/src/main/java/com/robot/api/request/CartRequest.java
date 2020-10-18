package com.robot.api.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartRequest {

    @NotNull(message = "sku不能为空")
    private String sku;

    private int quantity;
}
