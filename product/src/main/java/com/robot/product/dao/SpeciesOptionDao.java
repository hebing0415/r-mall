package com.robot.product.dao;


import com.robot.api.pojo.SpeciesOption;

import java.util.List;

public interface SpeciesOptionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SpeciesOption record);

    int insertSelective(SpeciesOption record);

    SpeciesOption selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpeciesOption record);

    int updateByPrimaryKey(SpeciesOption record);

    List<SpeciesOption> findSpeciesId(Integer id);
}