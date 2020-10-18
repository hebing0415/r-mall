package com.robot.member.service;

import com.robot.api.pojo.UserCoupon;
import com.robot.api.response.Message;
import com.robot.api.response.UserCouponResponse;

import java.util.List;
import java.util.Map;

public interface CouponService {

    /**
     * 发优惠券
     * @param uid
     * @param tplId
     * @return
     */
    Message sendCoupon(String uid, Integer tplId)throws Exception;


    /**
     * 查询优惠券
     * @param uid
     * @return
     * @throws Exception
     */
    Map<String, List<UserCouponResponse>> findCoupon(String uid) throws Exception;


    /**
     * 查询优惠券
     * @param uid
     * @param couponId
     * @return
     * @throws Exception
     */
    UserCoupon findCouponById(String uid,String couponId) throws Exception;
}
