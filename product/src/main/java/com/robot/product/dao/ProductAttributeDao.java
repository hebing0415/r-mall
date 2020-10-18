package com.robot.product.dao;

import com.robot.api.pojo.ProductAttribute;
import com.robot.api.pojo.ProductSku;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductAttributeDao {

    List<ProductAttribute> findAttributeList(@Param("skus") List<ProductSku> skus);

}
