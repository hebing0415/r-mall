package com.robot.api.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author robot
 * @date 2020/5/21
 * 商品属性表
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductAttributeOption implements Serializable {

    private int id;

    //属性名称
    private String name;

    //规格id
    private int attrId;

    //url
    private String imgUrl;

    private Integer sort;

    private Date createTime;

    private Date updateTime;
}
