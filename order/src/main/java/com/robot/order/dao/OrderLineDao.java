package com.robot.order.dao;


import com.robot.api.pojo.OrderLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderLineDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderLine record);

    int insertSelective(OrderLine record);

    OrderLine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderLine record);

    int updateByPrimaryKey(OrderLine record);

    int insertBatch(@Param("list") List<OrderLine> orderLines);

    OrderLine selectOrderLineByCode(String orderCode);
}
