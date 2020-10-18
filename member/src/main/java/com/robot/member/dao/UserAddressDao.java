package com.robot.member.dao;


import com.robot.api.pojo.UserAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAddressDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(Integer id);

    int updateAddress(UserAddress record);

    int updateByPrimaryKey(UserAddress record);

    List<UserAddress> findAddress(String uid);

    UserAddress findById(@Param("id") Integer id,
                         @Param("uid") String uid);

    int deleteById(@Param("id") Integer id,
                   @Param("uid") String uid);

    UserAddress findByDefault(String uid);
}
