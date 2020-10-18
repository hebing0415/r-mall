package com.robot.api.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CouponRequest {

    @NotNull(message = "券模板id不能为空")
    private Integer tplId;
}
