package com.robot.member.dao;


import com.robot.api.pojo.CouponTpl;

public interface CouponTplDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CouponTpl record);

    int insertSelective(CouponTpl record);

    CouponTpl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CouponTpl record);

    int updateByPrimaryKey(CouponTpl record);
}
