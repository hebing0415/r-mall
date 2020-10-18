package com.robot.api.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * payment_details
 * @author
 */
@Data
public class PaymentDetails implements Serializable {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 交易金额
     */
    private BigDecimal transactionAmount;

    /**
     * 交易时间
     */
    private Date transactionTime;

    /**
     * 微信支付订单号(交易单号)
     */
    private String transactionNo;


    private Integer paymentStatus;

    /**
     * 实付金额
     */
    private BigDecimal paidAmount;

    /**
     * 支付类型：
     */
    private Integer paymentType;

    private static final long serialVersionUID = 1L;
}
