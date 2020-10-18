package com.robot.api.response;

import com.robot.api.pojo.ProductAttributeOption;
import lombok.Data;

import java.util.List;

@Data
public class ProductTreeResponse {

    private String k;

    private String k_s ;

    private List<ProductAttributeOption> v;
}
