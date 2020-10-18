package com.robot.api.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * code
 */

@Data
public class Code implements Serializable {

    private String number;
    private String code;
    private Date createTime;
    private Date updateTime;
}
