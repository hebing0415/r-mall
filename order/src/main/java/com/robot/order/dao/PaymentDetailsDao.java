package com.robot.order.dao;


import com.robot.api.pojo.PaymentDetails;

public interface PaymentDetailsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PaymentDetails record);

    int insertSelective(PaymentDetails record);

    PaymentDetails selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PaymentDetails record);

    int updateByPrimaryKey(PaymentDetails record);
}
