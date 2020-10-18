package com.robot.api.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品规格list
 */
@Data
public class ProductListResponse {

    private String id;

    private Integer s1;

    private Integer s2;

    private BigDecimal price;

    private int stock_num;
}
