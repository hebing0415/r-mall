package com.robot.order.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.robot.api.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    int updateByOrderCode(@Param("status") Integer status,
                          @Param("orderCode")String orderCode);

    List<Order> orderRedDot(String uid);

    PageList<Order> orderListByStatus(@Param("uid")String uid,
                                      @Param("status")Integer status);

    void deleteByTime(Date outTime);
}
