package com.robot.product.service.impl;

import com.robot.api.pojo.Species;
import com.robot.api.pojo.SpeciesOption;
import com.robot.api.response.Message;
import com.robot.api.response.SpeciesListResponse;
import com.robot.product.dao.SpeciesDao;
import com.robot.product.dao.SpeciesOptionDao;
import com.robot.product.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private SpeciesDao speciesDao;

    @Resource
    private SpeciesOptionDao speciesOptionDao;

    @Override
    public Message findList(Integer id) {
        SpeciesListResponse speciesListResponse=new SpeciesListResponse();
        List<Species> species= speciesDao.findSpeciesList();
        List<SpeciesOption> speciesOptions= speciesOptionDao.findSpeciesId(id);
        speciesListResponse.setSpeciesList(species);
        speciesListResponse.setSpeciesOptions(speciesOptions);
        return new Message(speciesListResponse);
    }
}
