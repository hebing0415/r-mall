package com.robot.api.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author robot
 * @date 2019/12/25 16:21
 * 轮播图
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Carousel implements Serializable {


    /**
     * id
     */
    private int id;

    /**
     * name
     */
    private String name;

    /**
     * url
     */
    private String url;

    /**
     * type
     */
    private int type;

    /**
     * enable
     */
    private int enable;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * updateTime
     */
    private Date updateTime;

}
