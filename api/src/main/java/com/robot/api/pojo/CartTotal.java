package com.robot.api.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Cart Total
 *
 */

@Data
public class CartTotal {


    private BigDecimal total;

    private boolean selectAll;


}
