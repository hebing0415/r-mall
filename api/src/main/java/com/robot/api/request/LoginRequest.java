package com.robot.api.request;


import lombok.Data;

@Data
public class LoginRequest {

    private String phone;

    private String email;

    private String code;

    private String passWord;

    private String uid;

}
