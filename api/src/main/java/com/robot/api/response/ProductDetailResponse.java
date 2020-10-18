package com.robot.api.response;

import com.robot.api.pojo.ProductImg;
import com.robot.api.pojo.ProductRights;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponse {


    /**
     * name
     */
    private String name;

    /**
     * sku
     */
    private String sku;

    /**
     * 规格1
     */
    private Integer attrOne;

    /**
     * 规格2
     */
    private Integer attrTwo;

    /**
     * 产品编号
     */
    private int productId;


    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subtitle;

    /**
     * 商品关键字
     */
    private String keywords;

    /**
     * 标签
     */
    private String tags;

    /**
     * 虚拟购买量
     */
    private int virtualNum;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 市场价格
     */
    private BigDecimal marketPrice;

    /**
     * 库存
     */
    private int stock;

    /**
     * 封面图
     */
    private String pictureUrl;

    /**
     * 排序
     */
    private int sort;

    /**
     * 查询商品详情图片
     */
    private ProductImg productImg;

    /**
     * 品牌
     */
    private String brand;



    private  List<ProductRights> productRightsList;
}
