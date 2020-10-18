package com.robot.product.service.impl;

import com.robot.api.middleware.BaseRedis;
import com.robot.api.pojo.Carousel;
import com.robot.product.dao.CarouselDao;
import com.robot.product.service.CarouselService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author robot
 * @date 2019/12/26 14:26
 */
@Service
public class CarouselServiceImpl implements CarouselService {

    @Resource
    private CarouselDao carouselDao;

    @Resource
    private BaseRedis baseRedis;

    @Override
    public List<Carousel> findCarouselList(int type) {
        return getProductByRedis(type);
    }

    private List<Carousel> getProductByRedis(int type) {
        List<Carousel>         carousels = carouselDao.findCarouselList(type);
        ;
        return carousels;
//        String result = null;
//        try {
//            baseRedis.del(String.format(RedisKeyEnum.CAROUSEL_KEY.getKey(), type));
//
//            result = baseRedis.get(String.format(RedisKeyEnum.CAROUSEL_KEY.getKey(), type));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //如果redis存在就直接返回
//        if (StringUtils.isNotBlank(result)) {
//            carousels = JSON.parseObject(result, new TypeReference<List<Carousel>>() {
//            });
//            return carousels;
//        }
//        //如果不存在就查库
//        carousels = carouselDao.findCarouselList(type);
//        if (!CollectionUtils.isEmpty(carousels)) {
//            baseRedis.set(String.format(RedisKeyEnum.CAROUSEL_KEY.getKey(), type), JSON.toJSONString(carousels), StaticUtil.out_time);
//            return carousels;
//
//        }
//        return null;
    }

}
