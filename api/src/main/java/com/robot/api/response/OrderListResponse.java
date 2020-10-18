package com.robot.api.response;

import com.robot.api.pojo.Order;
import com.robot.api.pojo.OrderLine;
import lombok.Data;

@Data
public class OrderListResponse {

    private Order order;

    private long time;

    private String timeStr;

    private long total;

    private OrderLine orderLine;

}
