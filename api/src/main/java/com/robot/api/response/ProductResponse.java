package com.robot.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    /**
     * id
     */
    private int id;


    /**
     * productId
     */
    private String productId;


    /**
     * 名称
     */
    private String name;

    /**
     * title
     */
    private String title;

    /**
     * 主图
     */
    private String img;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private String stock;
}
