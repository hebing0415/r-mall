package com.robot.member.service.impl;

import com.robot.api.enums.ExpxEnum;
import com.robot.api.enums.NxxxEnum;
import com.robot.api.enums.RedisKeyEnum;
import com.robot.api.middleware.BaseRedis;
import com.robot.api.pojo.User;
import com.robot.api.pojo.UserAddress;
import com.robot.api.request.AddressRequest;
import com.robot.api.response.AddressListResponse;
import com.robot.api.response.ErrorType;
import com.robot.api.response.Message;
import com.robot.api.util.RandomCodeUtil;
import com.robot.api.util.StaticUtil;
import com.robot.member.dao.UserAddressDao;
import com.robot.member.dao.UserDao;
import com.robot.member.service.ConvertMemberService;
import com.robot.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author robot
 * @date 2020/1/7 11:29
 */
@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Resource
    private UserDao userDao;

    @Resource
    private UserAddressDao addressDao;

    @Resource
    private BaseRedis baseRedis;


    private final static String uidStr="101";

    @Override
    public User personCenter(String uid) {
       return userDao.selectByUid(uid);
    }

    /**
     * 根据手机号查询用户信息
     *
     * @param phone
     * @return
     */
    @Override
    public User findMember(String phone, String email) {
        return userDao.findByPhone(phone, email);
    }

    @Override
    public int updateUserLogin(User user) {
        return userDao.updateUserLogin(user);
    }

    @Override
    public boolean userRegister(User user) {
        user.setPassword(DigestUtils.md5DigestAsHex(RandomCodeUtil.createPwd().getBytes()));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setUserName(StaticUtil.default_name);
        user.setUserId(uidStr+ RandomCodeUtil.createCode());
        int result = userDao.userRegister(user);
        return result == 1;
    }

    @Override
    public UserAddress findDefault(String uid) {
        return addressDao.findByDefault(uid);
    }


    @Override
    public UserAddress findById(Integer id, String uid) {
        return addressDao.findById(id, uid);
    }

    @Override
    public Message addAddress(AddressRequest address) throws Exception {
        UserAddress userAddress = new UserAddress();
        userAddress = ConvertMemberService.INSTANCE.convertUserAddress(address);
        userAddress.setDeafult(address.convertDefault(address.getIsDefault()));
        userAddress.setCreateTime(new Date());
        userAddress.setUpdateTime(new Date());
        int result = addressDao.insert(userAddress);
        if (result == 1) {
            return Message.success(ErrorType.SUCCESS);
        }
        return Message.success(ErrorType.ADD_EXCEPTION);
    }

    @Override
    public Message findAddress(AddressRequest addressRequest, String uid) throws Exception {
        if (addressRequest != null) {
            UserAddress userAddress = addressDao.findById(addressRequest.getId(), uid);
            return Message.success(userAddress, ErrorType.SUCCESS);
        }
        List<UserAddress> userAddressList = addressDao.findAddress(uid);
        List<AddressListResponse> addressListResponses = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userAddressList)) {
            for (UserAddress userAddress : userAddressList) {
                addressListResponses.add(AddressListResponse.convert(userAddress));
            }
        }
        return Message.success(addressListResponses, ErrorType.SUCCESS);
    }

    @Override
    public Message updateAddress(AddressRequest addressRequest, String uid) throws Exception {
        String key = String.format(RedisKeyEnum.SET_ADDRESS_KEY.getKey(), uid);
        String redisResult = baseRedis.set(key, uid, NxxxEnum.nx.getKey(), ExpxEnum.ex.getKey(), 10);
        UserAddress userAddress = ConvertMemberService.INSTANCE.convertUserAddress(addressRequest);
        userAddress.setDeafult(addressRequest.convertDefault(addressRequest.getIsDefault()));
        try {
            if (StringUtils.equals(redisResult, "OK")) {
                if (addressRequest.convertDefault(addressRequest.getIsDefault()) == 1) {
                    UserAddress defaultAddress = addressDao.findByDefault(uid);
                    if (defaultAddress != null && !defaultAddress.getId().equals(addressRequest.getId())) {
                        defaultAddress.setDeafult(0);
                        addressDao.updateAddress(defaultAddress);
                    }
                }
                userAddress.setUpdateTime(new Date());
                int result = addressDao.updateAddress(userAddress);
                if (result == 1) {
                    return Message.success(ErrorType.SUCCESS);
                }
                return Message.error(ErrorType.UPDATE_EXCEPTION);
            }
        } finally {
            baseRedis.del(String.format(RedisKeyEnum.SET_ADDRESS_KEY.getKey(), uid));
        }
        return Message.success(ErrorType.TRY_AGAIN_LATER);
    }

    @Override
    public Message deleteAddress(AddressRequest request, String uid) throws Exception {
        int result = addressDao.deleteById(request.getId(), uid);
        if (result == 1) {
            return Message.success(ErrorType.SUCCESS);
        }
        return Message.error(ErrorType.UPDATE_EXCEPTION);
    }
}
