package com.robot.api.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * order_line
 *
 * @author
 */
@Data
public class OrderLine implements Serializable {
    //id
    private Integer id;

    //订单号
    private String orderCode;

    //sku
    private String sku;

    //产品名称
    private String productName;

    //数量
    private Integer quantity;

    //价格
    private BigDecimal price;

    private Date createTime;

    private Date updateTime;

    private String image;

    private static final long serialVersionUID = 1L;

    public static List<OrderLine> convertOrderLine(List<CartEntry> cartEntries, String orderCode) {
        List<OrderLine> orderLines=new ArrayList<>();
        for(CartEntry cartEntry:cartEntries) {
            OrderLine orderLine = new OrderLine();
            orderLine.setOrderCode(orderCode);
            orderLine.setQuantity(cartEntry.getQuantity());
            orderLine.setSku(cartEntry.getSku());
            orderLine.setProductName(cartEntry.getProductName());
            orderLine.setPrice(cartEntry.getPrice());
            orderLine.setCreateTime(new Date());
            orderLine.setUpdateTime(new Date());
            orderLine.setImage(cartEntry.getImage());
            orderLines.add(orderLine);
        }
        return orderLines;
    }
}
