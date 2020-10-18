package com.robot.product.provider;

import com.robot.api.pojo.ProductSku;
import com.robot.api.request.CategoryRequest;
import com.robot.api.response.ProductAttrResponse;
import com.robot.api.response.ProductResponse;
import com.robot.api.response.ProductDetailResponse;

import java.util.List;

/**
 * @author robot
 * @date 2020/1/3 16:04
 */
public interface ProductProvider {

    /**
     * 查询商品列表 分页加载
     * @param page
     * @param limit
     * @return
     */
    List<ProductResponse> productList(Integer optionId,Integer page, Integer limit) throws Exception;

    /**
     * 查询商品详情
     * @param sku
     */
    ProductDetailResponse productDetail(String sku) throws Exception;


    /**
     * 查询sku 商品
     */
    ProductSku productSku(String sku) throws Exception;

    /**
     * 查询商品规格
     * @param categoryRequest
     * @return
     */
    ProductAttrResponse ProductNorms(CategoryRequest categoryRequest);
}
