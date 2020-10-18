package com.robot.api.response;

import com.robot.api.pojo.UserAddress;
import com.robot.api.util.AccountValidatorUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class OrderSureResponse {


    /**
     * 用户购物车数据
     */
    private CartResponse cartResponse;


    /**
     * 用户优惠券数据
     */
    private Map<String, List<UserCouponResponse>> userCoupon;


    /**
     * 用户积分
     */
    private Integer point;

    /**
     * 运费
     */
    private BigDecimal freight = new BigDecimal(10);

    /**
     * 详细地址
     */
    private String addressDetail;

    /**
     * 地址id
     */
    private Integer addressId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 应付
     */
    private BigDecimal payAmount;


    /**
     * 优惠券优惠
     */
    private BigDecimal couponDiscount;


    public OrderSureResponse convertOrderSure(OrderSureResponse orderSureResponse, UserAddress userAddress) {
        orderSureResponse.setAddressId(userAddress.getId());
        orderSureResponse.setName(userAddress.getName());
        orderSureResponse.setPhone(AccountValidatorUtil.convertPhone(userAddress.getTel()));
        orderSureResponse.setAddressDetail(userAddress.getProvince() + userAddress.getCity() + userAddress.getCounty()
                + " " + userAddress.getAddressDetail());
        return orderSureResponse;
    }


}
