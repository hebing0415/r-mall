package com.robot.api.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//商品权益

@Data
public class ProductRights implements Serializable {

    private int id;

    private int type;

    private String icon;

    private String describe;

    private Date createTime;

    private Date updateTime;
}
