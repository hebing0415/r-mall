package com.robot.member.provider.impl;

import com.robot.api.pojo.User;
import com.robot.api.pojo.UserAddress;
import com.robot.api.pojo.UserCoupon;
import com.robot.api.request.AddressRequest;
import com.robot.api.response.ErrorType;
import com.robot.api.response.Message;
import com.robot.api.response.UserCouponResponse;
import com.robot.member.provider.MemberProvider;
import com.robot.member.service.CouponService;
import com.robot.member.service.MemberService;
import com.robot.member.service.RandomCodeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author robot
 * @date 2020/1/7 15:31
 */
@Service(version = "1.0.0", timeout = 5000)
@Slf4j
public class MemberProviderImpl implements MemberProvider {

    @Resource
    private MemberService memberService;

    @Resource
    private RandomCodeService randomCodeService;

    @Resource
    private CouponService couponService;


    @Override
    public User findMember(String phone, String email) {
        return memberService.findMember(phone, email);
    }

    @Override
    public User findMemberByUid(String uid) {
        return memberService.personCenter(uid);
    }


    @Override
    public int updateLoginUser(User user) {
        return memberService.updateUserLogin(user);
    }

    @Override
    public boolean userRegister(User user) {
        return memberService.userRegister(user);
    }

    @Override
    public boolean verify(String str, String input) {
        return randomCodeService.verify(str, input);
    }

    @Override
    public UserAddress findDefault(String uid) {
        return memberService.findDefault(uid);
    }

    @Override
    public Message addAddress(AddressRequest address) {
        try {
            return memberService.addAddress(address);
        } catch (Exception e) {
            log.info("addAddress add error address:{}", address, e);
            return Message.error(ErrorType.ADD_EXCEPTION);
        }
    }

    @Override
    public UserAddress findAddressById(Integer id, String uid) {
        try {
            return memberService.findById(id, uid);
        } catch (Exception e) {
            log.info("addAddress add error id:{},uid:{}", id, uid, e);
            return null;
        }
    }

    @Override
    public Message findAddress(AddressRequest addressRequest, String uid) {
        try {
            return memberService.findAddress(addressRequest, uid);
        } catch (Exception e) {
            log.info("findAddress error uid:{}", uid, e);
            return Message.error(ErrorType.ERROR);
        }
    }

    @Override
    public Message updateAddress(AddressRequest request, String uid) {
        try {
            return memberService.updateAddress(request, uid);
        } catch (Exception e) {
            log.info("updateAddress error uid:{}", uid, e);
            return Message.error(ErrorType.ERROR);
        }
    }

    @Override
    public Message deleteAddress(AddressRequest request, String uid) {
        try {
            return memberService.deleteAddress(request, uid);
        } catch (Exception e) {
            log.info("deleteAddress error uid:{}", uid, e);
            return Message.error(ErrorType.ERROR);
        }
    }

    @Override
    public Message sendCoupon(String uid, Integer tplId) {
        try {
            return couponService.sendCoupon(uid, tplId);
        } catch (Exception e) {
            log.info("sendCoupon error uid:{},tplId:{}", uid, tplId, e);
            return Message.error(ErrorType.ERROR);
        }
    }

    @Override
    public Map<String, List<UserCouponResponse>> findCoupon(String uid) {
        try {
            return couponService.findCoupon(uid);
        } catch (Exception e) {
            log.info("findCoupon error uid:{}", uid, e);
            return null;
        }
    }

    @Override
    public UserCoupon findCouponById(String uid,String couponId) {
        try {
            return couponService.findCouponById(uid,couponId);
        } catch (Exception e) {
            log.info("findCoupon error uid:{}", uid, e);
            return null;
        }
    }

    @Override
    public User findUserByUid(String uid) {
        try {
            return memberService.personCenter(uid);
        } catch (Exception e) {
            log.info("findCoupon error uid:{}", uid, e);
            return null;
        }
    }
}
