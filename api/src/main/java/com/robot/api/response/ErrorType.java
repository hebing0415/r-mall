package com.robot.api.response;

/**
 * @author robot
 * @date 2020/1/7 16:03
 */
public enum ErrorType {

    SUCCESS("0000", "成功"),
    ERROR("9999", "失败"),
    PASSWORD_ERROR("1007","密码错误"),
    SIGN_ERROR("1006","sign错误"),
    TOKE_ERROR("1005","token解析异常"),
    TOKEN_EXPIRES("1004","token过期"),
    LOGIN_SUCCESS("1003", "登录成功"),
    LOGIN_MESSAGE("1002", "手机号或验证码不能为空"),
    PLEASE_REGISTER("1001", "账号不存在，请先注册!"),
    USER_NOT_LOGIN("1008","用户未登录"),
    SEND_EMAIL_ERROR("1009","发送失败"),
    PHONE_ERROR("1010","请输入正确手机号"),
    EMAIL_ERROR("'1011","请输入正确邮箱"),
    EMAIL_TIMES_EXCEED("1012","Email次数超过"),
    SMS_TIMES_TIMES("1015","短信次数超过"),
    CODE_ERROR("1013","请输入正确的验证码"),
    ACCOUNT_EXIT("1014","账号已存在"),
    PRODUCT_NOT_EXIT("1015","商品不存在"),
    ADD_EXCEPTION("1016","添加失败"),
    UPDATE_EXCEPTION("1017","更新失败"),
    TRY_AGAIN_LATER("1018","请稍后重试"),
    COUPON_TPL_NOT_EXIST("1019","优惠券不存在"),
    COUPON_NUM_MAX("1020","优惠券已发完"),
    COUPON_EXPIRE("1021","优惠券已过期"),
    COUPON_NOT_UP("1022","优惠券时间未到"),
    COUPON_IS_USE("1023","优惠券不存在或已核销"),
    COUPON_THRESHOLD("1024","优惠券未达到门槛"),
    USER_NOT_EXIT("1025","用户名不存在"),


    ;
    private String errorCode;

    private String errorMsg;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

     ErrorType(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

}
