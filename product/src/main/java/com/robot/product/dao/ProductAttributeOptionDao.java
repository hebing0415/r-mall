package com.robot.product.dao;

import com.robot.api.pojo.ProductAttribute;
import com.robot.api.pojo.ProductAttributeOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductAttributeOptionDao {


    List<ProductAttributeOption> findOptionList(@Param("productAttributes") List<ProductAttribute> productAttributes,
                                                @Param("productId") String productId);

    List<ProductAttributeOption> findOptionByList(@Param("productAttributes") List<ProductAttribute> productAttributes);

    ProductAttributeOption findByAttrId(Integer attrId);
}
