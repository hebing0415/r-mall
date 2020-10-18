package com.robot.member.provider;

import com.robot.api.pojo.User;
import com.robot.api.pojo.UserAddress;
import com.robot.api.pojo.UserCoupon;
import com.robot.api.request.AddressRequest;
import com.robot.api.response.Message;
import com.robot.api.response.UserCouponResponse;

import java.util.List;
import java.util.Map;

/**
 * dubbo 接口
 *
 * @author robot
 * @date 2020/1/7 15:31
 */
public interface MemberProvider {

    /**
     * 查询用户
     * @param phone
     * @param email
     * @return
     */
    User findMember(String phone, String email);

    /**
     * 根据uid查询用户
     * @param uid
     * @return
     */
    User findMemberByUid(String uid);

    /**
     * 更新用户的登录日志
     * @param user
     * @return
     */
    int updateLoginUser(User user);

    /**
     * 用户注册
     * @param user
     * @return
     */
    boolean userRegister(User user);

    /**
     * 验证输入
     * @param str
     * @param input
     * @return
     */
    boolean verify(String str, String input);

    /**
     * 查询默认
     * @param uid
     * @return
     */
    UserAddress findDefault(String uid);

    /**
     * 查询地址根据id
     * @param id
     * @param uid
     * @return
     */
    UserAddress findAddressById(Integer id,String uid);

    /**
     * 添加地址
     * @param request
     * @return
     */
    Message addAddress(AddressRequest request);

    /**
     * 查询地址
     * @param addressRequest
     * @param uid
     * @return
     */
    Message findAddress(AddressRequest addressRequest,String uid);

    /**
     * 更新地址
     * @param request
     * @param uid
     * @return
     */
    Message updateAddress(AddressRequest request, String uid);

    /**
     * 删除地址
     * @param request
     * @param uid
     * @return
     */
    Message deleteAddress(AddressRequest request, String uid);

    /**
     * 发送优惠券
     * @param uid
     * @param tplId
     * @return
     */
    Message sendCoupon(String uid,Integer tplId);


    /**
     * 查询优惠券
     * @param uid
     * @return
     */
    Map<String, List<UserCouponResponse>> findCoupon(String uid);


    /**
     * 查询券id
     */
    UserCoupon findCouponById(String uid,String couponId);

    /**
     *
     * @param uid
     * @return
     */
    User findUserByUid(String uid);
}
