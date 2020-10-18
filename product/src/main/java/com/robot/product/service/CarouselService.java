package com.robot.product.service;

import com.robot.api.pojo.Carousel;

import java.util.List;

/**
 * @author robot
 * @date 2019/12/26 14:26
 */
public interface CarouselService {


    /**
     *
     * @param type
     * @return
     */
    List<Carousel> findCarouselList(int type);
}
