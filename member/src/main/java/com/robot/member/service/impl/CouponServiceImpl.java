package com.robot.member.service.impl;

import com.robot.api.enums.RedisKeyEnum;
import com.robot.api.middleware.BaseRedis;
import com.robot.api.middleware.MongoDBUtil;
import com.robot.api.pojo.CouponTpl;
import com.robot.api.pojo.UserCoupon;
import com.robot.api.response.ErrorType;
import com.robot.api.response.Message;
import com.robot.api.response.UserCouponResponse;
import com.robot.api.util.RandomCodeUtil;
import com.robot.member.dao.CouponTplDao;
import com.robot.member.service.CouponService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CouponServiceImpl implements CouponService {


    @Resource
    private CouponTplDao couponTplDao;

    @Resource
    private MongoDBUtil mongoDBUtil;

    @Resource
    private BaseRedis baseRedis;

    private static final String couponCollection = "userCoupon";

    private static final String uidStr = "uid";

    private static final String couponIdStr = "couponId";

    private static final String sort = "startAt";

    @Transactional(rollbackFor = Exception.class)
    public Message sendCoupon(String uid, Integer tplId) throws Exception {
        CouponTpl couponTpl = couponTplDao.selectByPrimaryKey(tplId);
        if (couponTpl == null) {
            return Message.error(ErrorType.COUPON_TPL_NOT_EXIST);
        }
        if (couponTpl.getQuantity() < couponTpl.getSendQuantity()) {
            return Message.error(ErrorType.COUPON_NUM_MAX);
        }
        String couponId = createCouponId(tplId, couponTpl.getValidDate(), couponTpl.getInvalidDate());
        UserCoupon userCoupon = UserCoupon.convertUserCoupon(uid, couponId, couponTpl);
        MongoDBUtil.save(userCoupon, couponCollection);
        CouponTpl updateCouponTpl = new CouponTpl();
        updateCouponTpl.setId(tplId);
        updateCouponTpl.setSendQuantity(couponTpl.getSendQuantity());
        couponTplDao.updateByPrimaryKeySelective(updateCouponTpl);
        return Message.success(ErrorType.SUCCESS);
    }


    public Map<String, List<UserCouponResponse>> findCoupon(String uid) throws Exception {
        UserCoupon coupons = new UserCoupon();
        Map<String, List<UserCouponResponse>> map = new HashMap<>();
        List<UserCoupon> userCouponList = (List<UserCoupon>) MongoDBUtil.find(coupons, uidStr, uid, couponCollection, sort);
        List<UserCouponResponse> responses = new ArrayList<>();
        List<UserCouponResponse> notUserCoupon = new ArrayList<>();
        for (UserCoupon userCoupon : userCouponList) {
            UserCouponResponse userCouponResponse = new UserCouponResponse();
            userCouponResponse.setCouponId(userCoupon.getCouponId());
            userCouponResponse.setCouponTplId(userCoupon.getCouponTplId());
            userCouponResponse.setName(userCoupon.getCouponName());
            userCouponResponse.setCondition(userCoupon.getThreshold());
            userCouponResponse.setStartAt(userCoupon.getStartAt().getTime() / 1000);
            userCouponResponse.setEndAt(userCoupon.getEndAt().getTime() / 1000);
            BigDecimal value = userCoupon.getAmount().multiply(new BigDecimal("100"));
            userCouponResponse.setValue(value.setScale(0, BigDecimal.ROUND_DOWN).longValue());
            userCouponResponse.setValueDesc(userCoupon.getAmount().toString());
            userCouponResponse.setUnitDesc("元");
            if (userCoupon.getStartAt().before(new Date()) && userCoupon.getEndAt().after(new Date())) {
                userCouponResponse.setDescription(userCoupon.getDescription());
                responses.add(userCouponResponse);
            } else if (userCoupon.getStartAt().after(new Date())) {
                userCouponResponse.setCondition(ErrorType.COUPON_NOT_UP.getErrorMsg());
                notUserCoupon.add(userCouponResponse);
            } else if (userCoupon.getEndAt().before(new Date())) {
                userCouponResponse.setCondition(ErrorType.COUPON_EXPIRE.getErrorMsg());
                notUserCoupon.add(userCouponResponse);
            }
        }
        map.put("sureCoupon", responses);
        map.put("notUserCoupon", notUserCoupon);
        return map;
    }


    @Override
    public UserCoupon findCouponById(String uid, String couponId) throws Exception {
        UserCoupon userCoupon=new UserCoupon();
        String[] findKeys = {uidStr, couponIdStr};
        Object[] findValues = {uid, couponId};
        return (UserCoupon) MongoDBUtil.findOne(userCoupon, findKeys, findValues, couponCollection);
    }


    //创建优惠券id
    public String createCouponId(Integer tplId, Date startDate, Date endDate) {
        String id = RandomCodeUtil.getOrderCode();
        int times = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String key = String.format(String.format(RedisKeyEnum.COUPON_ID.getKey(), tplId), sdf.format(new Date()));
        boolean result = baseRedis.sismember(key, id);
        while (result && (times < 20)) {
            times++;
            id = RandomCodeUtil.getOrderCode();
        }
        baseRedis.sadd(key, id);
        baseRedis.expire(key, (int) (endDate.getTime() - startDate.getTime()));
        return id;
    }

}
