package com.robot.product.dao;

import com.robot.api.pojo.ProductImg;

public interface ProductImgDao {


    /**
     *  查询商详的图片
     * @param productId
     * @return
     */
    ProductImg findProductImg(String productId);

}
