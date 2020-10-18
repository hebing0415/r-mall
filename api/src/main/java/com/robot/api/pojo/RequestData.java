package com.robot.api.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 前端请求通用数据
 * @author robot
 * @date 2019/12/9 16:46
 */
@Data
public class RequestData implements Serializable {

    /**
     * uid
     */
    private String uid;

    /**
     * sku
     */
    private String sku;

}
