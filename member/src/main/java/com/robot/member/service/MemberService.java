package com.robot.member.service;

import com.robot.api.pojo.User;
import com.robot.api.pojo.UserAddress;
import com.robot.api.request.AddressRequest;
import com.robot.api.response.Message;

/**
 * @author robot
 * @date 2019/12/9 14:33
 */
public interface MemberService {


    /**
     * 根据uid查询
     * @param uid
     * @return
     */
    User personCenter(String uid);

    /**
     * 查询会员信息
     *
     * @param phone
     * @return
     */
    User findMember(String phone, String email);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    int updateUserLogin(User user);

    /**
     * 手机号注册
     *
     * @param user
     * @return
     */
    boolean userRegister(User user);

    /**
     * 查询默认地址
     *
     * @param uid
     * @return
     */
    UserAddress findDefault(String uid);


    /**
     * 根据id查询地址
     * @param id
     * @param uid
     * @return
     */
    UserAddress findById(Integer id, String uid);

    /**
     * 添加地址
     * @param request
     * @return
     * @throws Exception
     */
    Message addAddress(AddressRequest request) throws Exception;

    /**
     * 查询地址列表
     * @param request
     * @param uid
     * @return
     * @throws Exception
     */
    Message findAddress(AddressRequest request, String uid) throws Exception;

    /**
     * 更新地址
     * @param request
     * @param uid
     * @return
     * @throws Exception
     */
    Message updateAddress(AddressRequest request, String uid) throws Exception;

    /**
     * 删除地址
     * @param request
     * @param uid
     * @return
     * @throws Exception
     */
    Message deleteAddress(AddressRequest request, String uid) throws Exception;


}
