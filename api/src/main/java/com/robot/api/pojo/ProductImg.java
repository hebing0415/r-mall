package com.robot.api.pojo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author robot
 * @date 2020/5/20
 */

@Data
public class ProductImg implements Serializable {


    private int id;

    private String productId;

    //轮播图
    private String rollImg;

    //详情图
    private String detailImg;

    private Date createTime;

    private Date updateTime;

}
