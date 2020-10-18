package com.robot.product.dao;


import com.robot.api.pojo.Species;

import java.util.List;

public interface SpeciesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Species record);

    int insertSelective(Species record);

    Species selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Species record);

    int updateByPrimaryKey(Species record);

    List<Species> findSpeciesList();

    List<Species> findList();
}