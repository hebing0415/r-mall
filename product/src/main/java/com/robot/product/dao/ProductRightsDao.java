package com.robot.product.dao;

import com.robot.api.pojo.ProductRights;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductRightsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductRights record);

    int insertSelective(ProductRights record);

    ProductRights selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductRights record);

    int updateByPrimaryKey(ProductRights record);

    List<ProductRights> selectProductRights(@Param("str") List<String> str);
}
