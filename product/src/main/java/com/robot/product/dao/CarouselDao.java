package com.robot.product.dao;

import com.robot.api.pojo.Carousel;

import java.util.List;

/**
 * @author robot
 * @date 2019/12/25 17:37
 */
public interface CarouselDao{


    /**
     * 查询首页轮播和标签图
     *
     * @param type
     * @return
     */
     List<Carousel> findCarouselList(int type);
}
