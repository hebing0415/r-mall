package com.robot.product.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.robot.api.pojo.ProductSku;

import java.util.List;

/**
 * @author robot
 * @date 2019/12/10 17:42
 */

public interface ProductSkuDao {

    /**
     * 分页查询商品
     *
     * @param pageBounds
     * @return
     */
    List<ProductSku> findProductSkuList(PageBounds pageBounds);


    /**
     * 查询当前产品所有的商品
     * @param productId
     * @return
     */
    List<ProductSku> findProductByProductId(String productId);

    /**
     * 查询当前产品所有的商品
     * @param productId
     * @return
     */
    List<ProductSku> findProductByProductIdSimple(String productId);

    /**
     * 查询商品详情
     * @param sku
     * @return
     */
    ProductSku findProductSkuDetail(String sku);
}
