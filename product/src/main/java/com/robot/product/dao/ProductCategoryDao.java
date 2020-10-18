package com.robot.product.dao;

import com.robot.api.pojo.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {

    /**
     * 查询属目
     * @param productId
     * @return
     */
    List<ProductCategory> findProductCategory(String productId);
}
