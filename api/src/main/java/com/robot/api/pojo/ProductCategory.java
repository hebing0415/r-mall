package com.robot.api.pojo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ProductCategory implements Serializable {

    private Integer id;

    private String cateName;

    private int sort;

    private String productId;

    private Date createTime;

    private Date updateTime;

    private List<ProductAttribute> attributes;

}
