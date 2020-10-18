package com.robot.api.response;

import com.robot.api.pojo.User;
import lombok.Data;

@Data
public class PersonCenterResponse {


    private int tobePay;
    private int pay ;
    private int tobeDelivered;
    private int received ;
    private User user;
}
