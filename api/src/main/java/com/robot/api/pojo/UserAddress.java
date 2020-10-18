package com.robot.api.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * user_address
 * @author
 */
@Data
public class UserAddress implements Serializable {
    private Integer id;

    private String name;

    private String uid;

    private String tel;

    private String province;

    private String city;

    private String county;

    private String addressDetail;

    private String areaCode;

    private String postalCode;

    private Integer deafult;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
