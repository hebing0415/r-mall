package com.robot.api.request;

import lombok.Data;

@Data
public class ProductListRequest {

    private Integer optionId;

    private Integer pageNum;

    private Integer pageSize;
}
