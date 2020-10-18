package com.robot.api.request;

import lombok.Data;

@Data
public class OrderListRequest {

    private Integer status;

    private Integer pageNumber;

    private Integer pageSize;

}
