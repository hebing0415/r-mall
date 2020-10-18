package com.robot.api.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品行
 */
@Data
public class CartEntry {

    private String sku;

    private String productName;

    private int quantity;

    private BigDecimal price;

    private String image;

    private boolean checked;

    private String title;

    private String attrOneName;

    private String attrTwoName;

    public static CartEntry convertCartEntry(String sku, int quantity, ProductSku product, boolean check) {
        CartEntry cartEntry = new CartEntry();
        cartEntry.setSku(sku);
        cartEntry.setQuantity(quantity);
        cartEntry.setProductName(product.getName());
        cartEntry.setImage(product.getPictureUrl());
        cartEntry.setPrice(product.getPrice());
        cartEntry.setChecked(check);
        cartEntry.setTitle(product.getTitle());
        cartEntry.setAttrOneName(product.getAttrOneName());
        cartEntry.setAttrTwoName(product.getAttrTwoName());
        return cartEntry;
    }

}
