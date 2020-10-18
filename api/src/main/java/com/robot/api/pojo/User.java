package com.robot.api.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author robot
 * @date 2020/1/7 10:22
 */
@Data
public class User implements Serializable {

    private int id;

    private String userId;

    private String phone;

    private String email;

    private int userStatus;

    private String password;

    private int registerSource;

    private String registerIp;

    private String userName;

    private Date updateTime;

    private Date createTime;

    private String headImg;

}
