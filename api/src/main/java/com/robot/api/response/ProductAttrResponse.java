package com.robot.api.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductAttrResponse {

    private String sku;

    private List<ProductTreeResponse> tree;

    private List<ProductListResponse> list;

    private BigDecimal price;

    private int stock_num;

    private String collection_id;

    private boolean none_sku;

    private String messages;

    private boolean hide_stock;

}
