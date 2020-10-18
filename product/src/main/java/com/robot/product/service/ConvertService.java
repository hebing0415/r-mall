package com.robot.product.service;


import com.robot.api.pojo.Product;
import com.robot.api.response.ProductDetailResponse;
import com.robot.api.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ConvertService {

    ConvertService INSTANCE = Mappers.getMapper( ConvertService.class );

    ProductDetailResponse convertProductSku(Product product) throws Exception;

    List<ProductResponse> convertProduct(List<Product> product) throws Exception;

}
