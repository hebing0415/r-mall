package com.robot.api.request;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class AddressRequest implements Serializable {
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

    private String isDefault;


    public Integer convertDefault(String isDefault) {
        if (StringUtils.equals(isDefault, "true")) {
            return 1;
        }
        if (StringUtils.equals(isDefault, "false")) {
            return 0;
        }
        return null;
    }
}
