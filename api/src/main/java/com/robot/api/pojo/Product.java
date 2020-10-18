package com.robot.api.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author robot
 * @date 2019/12/19 15:17
 */
@Data
public class Product implements Serializable {

    /**
     * id
     */
    private int id;


    /**
     * productId
     */
    private String productId;


    /**
     * 分类Id
     */
    private Integer speciesOptionId;

    /**
     * 名称
     */
    private String name;

    /**
     * 是否推荐
     */
    private Integer recommend;

    /**
     * 虚拟购买量
     */
    private Integer virtualNum;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 权益
     */
    private String rights;

    /**
     * 开启
     */
    private Integer enable;

    /**
     * title
     */
    private String title;

    /**
     * 主图
     */
    private String img;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 品牌
     */
    private String brand;
}
