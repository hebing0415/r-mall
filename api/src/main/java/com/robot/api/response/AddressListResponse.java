package com.robot.api.response;

import com.robot.api.pojo.UserAddress;
import lombok.Data;

/**
 *
 * 地址列表返回
 */

@Data
public class AddressListResponse {

    private Integer id;

    private String name;

    private String tel;

    private String address;

    private String isDefault;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public static AddressListResponse convert(UserAddress userAddress) {
        AddressListResponse addressResponse = new AddressListResponse();
        addressResponse.setId(userAddress.getId());
        addressResponse.setName(userAddress.getName());
        addressResponse.setTel(userAddress.getTel());
        addressResponse.setAddress(userAddress.getProvince() + userAddress.getCity() + userAddress.getCounty()
                + " " + userAddress.getAddressDetail());
        addressResponse.setIsDefault((userAddress.getDeafult() == 1) ? "true" : "");
        return addressResponse;
    }


}
