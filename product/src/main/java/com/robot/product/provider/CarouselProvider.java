package com.robot.product.provider;

import com.robot.api.pojo.Carousel;
import com.robot.api.response.Message;

import java.util.List;

/**
 * @author robot
 * @date 2020/1/3 16:02
 */
public interface CarouselProvider {

    List<Carousel> findCarouselList(int type);

    Message findSpeciesList(Integer id);
}

