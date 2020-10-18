package com.robot.product.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.robot.api.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author robot
 * @date 2019/12/19 15:42
 */
public interface ProductDao {

    /**
     * 查询商品列表
     *
     * @param pageBounds
     * @return
     */
    List<Product> findProductList(@Param("speciesOptionId") Integer speciesOptionId, PageBounds pageBounds);


    /**
     * 查询单个产品
     * @param productId
     * @return
     */
    Product findProductOne(String productId);



}
