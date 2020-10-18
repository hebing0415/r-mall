package com.robot.product.provider.impl;

import com.robot.api.pojo.Carousel;
import com.robot.api.response.Message;
import com.robot.product.provider.CarouselProvider;
import com.robot.product.service.CarouselService;
import com.robot.product.service.CategoryService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品服务的提供者
 *
 * @author robot
 * @date 2019/12/26 15:09
 */
@Service(version = "1.0.0", timeout = 5000)
public class CarouselProviderImpl implements CarouselProvider {

    @Resource
    private CarouselService carouselService;

    @Resource
    private CategoryService categoryService;

    public List<Carousel> findCarouselList(int type) {
        return carouselService.findCarouselList(type);

    }


    @Override
    public Message findSpeciesList(Integer id) {
        return categoryService.findList(id);
    }
}
