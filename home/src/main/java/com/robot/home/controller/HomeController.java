package com.robot.home.controller;

import com.robot.api.pojo.Carousel;
import com.robot.api.request.CarouseRequest;
import com.robot.api.request.ProductListRequest;
import com.robot.api.response.ErrorType;
import com.robot.api.response.Message;
import com.robot.api.response.ProductResponse;
import com.robot.home.annotation.ControllerLog;
import com.robot.notify.service.AliYunSMSService;
import com.robot.product.provider.CarouselProvider;
import com.robot.product.provider.ProductProvider;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 首页
 *
 * @author robot
 * @date 2019/12/25 11:31
 */
@Controller
@RequestMapping("/home")
@CrossOrigin
public class HomeController {

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Reference(version = "1.0.0", check = false)
    private CarouselProvider carouselProvider;

    @Reference(version = "1.0.0", check = false)
    private ProductProvider productProvider;


    @Reference(version = "1.0.0", check = false)
    private AliYunSMSService aliyunSMSService;

    @RequestMapping("/carouselList")
    @ResponseBody
    @ControllerLog(description = "首页分类list")
    public Message carouselList(@RequestBody CarouseRequest carouseRequest) {
        int type = carouseRequest.getType();
        logger.info("productList type:{}", type);
        List<Carousel> carousels;
        try {
            carousels = carouselProvider.findCarouselList(type);
        } catch (Exception e) {
            logger.error("productList error e", e);
            return Message.error(ErrorType.ERROR);
        }
        return Message.success(carousels);
    }

    @RequestMapping("/productList")
    @ResponseBody
    @ControllerLog(description = "查询商品列表")
    public Message productList(@RequestBody ProductListRequest request) {
        int page = request.getPageNum();
        int limit = request.getPageSize();
        Integer optionId = null;
        logger.info("productList page:{},limit:{}", page, limit);
        List<ProductResponse> products;
        try {
            if (request.getOptionId() != null) {
                optionId = request.getOptionId();
            }
            products = productProvider.productList(optionId, page, limit);
        } catch (Exception e) {
            logger.error("productList error e", e);
            return Message.error(ErrorType.ERROR);
        }
        return Message.success(products);
    }
}
