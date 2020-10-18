package com.robot.member.service;

import com.robot.api.pojo.UserAddress;
import com.robot.api.request.AddressRequest;
import com.robot.api.response.AddressListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ConvertMemberService {

    ConvertMemberService INSTANCE = Mappers.getMapper(ConvertMemberService.class);

    UserAddress convertUserAddress(AddressRequest addressRequest);

    List<AddressListResponse> convertAddAddress(List<UserAddress> userAddresses);
}
