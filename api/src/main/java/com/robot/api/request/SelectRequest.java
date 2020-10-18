package com.robot.api.request;

import lombok.Data;

@Data
public class SelectRequest {

    private boolean check;

    private String sku;

    private boolean selectAll;
}
