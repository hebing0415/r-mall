package com.robot.api.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author robot
 * @date 2020/5/21
 * 规格表
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductAttribute implements Serializable {


    private Integer s1;

    //type
    private Integer type;

    //是否是多选
    private Integer multiple;

    //规格名称
    private String attrName;

    private Integer sort;

    private List<ProductAttributeOption> options;

    private Date createTime;

    private Date updateTime;


}


