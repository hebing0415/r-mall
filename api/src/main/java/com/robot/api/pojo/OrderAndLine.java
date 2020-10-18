package com.robot.api.pojo;

import lombok.Data;

import java.util.List;

/**
 *  订单和订单行
 */

@Data
public class OrderAndLine {

    private Order order;

    private List<OrderLine> orderLine;
}
