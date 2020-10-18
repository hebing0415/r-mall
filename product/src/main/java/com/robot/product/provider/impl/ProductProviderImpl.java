package com.robot.product.provider.impl;

import com.robot.api.pojo.ProductSku;
import com.robot.api.request.CategoryRequest;
import com.robot.api.response.ProductAttrResponse;
import com.robot.api.response.ProductResponse;
import com.robot.api.response.ProductDetailResponse;
import com.robot.product.provider.ProductProvider;
import com.robot.product.service.ProductService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author robot
 * @date 2020/1/3 11:20
 */
@Service(version = "1.0.0", timeout = 10000)
public class ProductProviderImpl implements ProductProvider {

    @Resource
    private ProductService productService;


    @Override
    public List<ProductResponse> productList(Integer optionId,Integer page, Integer limit) throws Exception {
        return productService.productList(optionId,page, limit);
    }


    @Override
    public ProductDetailResponse productDetail(String productId) throws Exception {
        return productService.productDetail(productId);
    }

    @Override
    public ProductSku productSku(String sku) throws Exception {
        return productService.productSku(sku);
    }

    @Override
    public ProductAttrResponse ProductNorms(CategoryRequest categoryRequest) {
        return productService.ProductNorms(categoryRequest);
    }
}
