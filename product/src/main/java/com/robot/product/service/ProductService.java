package com.robot.product.service;

import com.robot.api.pojo.ProductSku;
import com.robot.api.request.CategoryRequest;
import com.robot.api.response.ProductAttrResponse;
import com.robot.api.response.ProductResponse;
import com.robot.api.response.ProductDetailResponse;

import java.util.List;

/**
 * @author robot
 * @date 2019/12/10 17:36
 */
public interface ProductService {

    /**
     * 查询商品列表
     * @param page
     * @param limit
     * @return
     */
    List<ProductResponse> productList(Integer optionId,Integer page, Integer limit) throws Exception;


    /**
     * 查询商品详情 by mysql
     * @param categoryRequest
     * @return
     */

    ProductAttrResponse ProductNorms(CategoryRequest categoryRequest);


    /**
     * 获取商品的详情
     */
    ProductDetailResponse productDetail(String sku) throws Exception;



    ProductSku productSku(String sku) throws Exception;

    /**
     * 查询商品详情 by redis
     * @param sku
     * @return
     */
    ProductSku getProductByRedis(String sku);



}
