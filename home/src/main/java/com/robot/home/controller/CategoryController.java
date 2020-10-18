package com.robot.home.controller;

import com.robot.api.request.CategoryListRequest;
import com.robot.api.response.Message;
import com.robot.home.annotation.ControllerLog;
import com.robot.product.provider.CarouselProvider;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    @Reference(version = "1.0.0", check = false)
    private CarouselProvider carouselProvider;


    @RequestMapping("/list")
    @ResponseBody
    @ControllerLog(description = "查询分类")
    public Message addCartEntry(@RequestBody CategoryListRequest categoryListRequest) throws Exception {
        return carouselProvider.findSpeciesList(categoryListRequest.getId());
    }
}
