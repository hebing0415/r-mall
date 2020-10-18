package com.robot.api.pojo;

import com.robot.api.enums.OrderStatusEnum;
import com.robot.api.request.CreateOrderRequest;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * order
 *
 * @author
 */
@Data
public class Order implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * uid
     */
    private String uid;

    /**
     * 应付金额
     */
    private BigDecimal payAmount;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 运费
     */
    private BigDecimal freightAmount;

    /**
     * 积分
     */
    private Integer point;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 地址id
     */
    private Integer addressId;

    /**
     * 券模板id
     */
    private Integer couponTplId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 快递单号
     */
    private String expressCode;

    /**
     * 快递公司
     */
    private String expressCompany;

    /**
     * 收货时间
     */
    private Date receiptTime;

    /**
     * 发货时间
     */
    private Date shipTime;

    /**
     * 商品数量
     */
    private Integer totalNum;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    private static final long serialVersionUID = 1L;

    public static Order convertOrder(UserCoupon userCoupon, CreateOrderRequest createOrderRequest,
                                     BigDecimal payAmount, BigDecimal orderAmount, BigDecimal freightAmount,
                                     int point, String orderCode) {
        Order order = new Order();
        order.setOrderCode(orderCode);
        order.setAddressId(createOrderRequest.getAddressId());
        order.setRemark(createOrderRequest.getRemark());
        order.setPayAmount(payAmount);
        order.setOrderAmount(orderAmount);
        order.setFreightAmount(freightAmount);
        order.setDiscountAmount(payAmount.subtract(orderAmount));
        order.setPoint(point);
        order.setOrderStatus(OrderStatusEnum.TO_BE_PAID.getCode());
        if (userCoupon != null) {
            order.setCouponTplId(userCoupon.getCouponTplId());
        }
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        return order;
    }
}
