package com.robot.home.controller;


import com.robot.api.request.CategoryRequest;
import com.robot.api.request.ProductDetailRequest;
import com.robot.api.response.Message;
import com.robot.home.annotation.ControllerLog;
import com.robot.product.provider.ProductProvider;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Reference(version = "1.0.0",check = false)
    private ProductProvider productProvider;

    @ResponseBody
    @RequestMapping("/detail")
    @ControllerLog(description = "商品详情")
    public  Message productDetail(@RequestBody ProductDetailRequest productDetailRequest) throws Exception {
        return Message.success(productProvider.productDetail(productDetailRequest.getProductId()));
    }

    @ResponseBody
    @RequestMapping("/category")
    @ControllerLog(description = "商品分类")
    public Message category(@RequestBody CategoryRequest categoryRequest) {
        return Message.success(productProvider.ProductNorms(categoryRequest));

    }
}
