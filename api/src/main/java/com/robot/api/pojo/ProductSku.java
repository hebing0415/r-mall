package com.robot.api.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 *
 * @author robot
 * @date 2019/12/11 11:45
 */
@Data
public class ProductSku implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * name
     */
    private String name;

    /**
     * sku
     */
    private String sku;

    /**
     * 商品规格一
     */
    private int attrOne;


    /**
     * 商品规格二
     */
    private int attrTwo;

    /**
     * 商品规格一名称
     */
    private String attrOneName;

    /**
     * 商品规格二名称
     */
    private String attrTwoName;

    /**
     * 产品编号
     */
    private String productId;


    /**
     * 标题
     */
    private String title;


    /**
     * 商品关键字
     */
    private String keywords;

    /**
     * 标签
     */
    private String tags;


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
     * 库存警告
     */
    private int warnStock;

    /**
     * 封面图
     */
    private String pictureUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
