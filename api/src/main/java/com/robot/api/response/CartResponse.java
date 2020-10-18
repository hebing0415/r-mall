package com.robot.api.response;

import com.robot.api.pojo.CartEntry;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartResponse {


    private BigDecimal total;

    private List<CartEntry> cartEntries;

    private boolean selectAll;

    private int totalNum;

}
