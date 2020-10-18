package com.robot.api.response;

import com.robot.api.pojo.Species;
import com.robot.api.pojo.SpeciesOption;
import lombok.Data;

import java.util.List;

@Data
public class SpeciesListResponse {


    private List<Species> speciesList;

    private List<SpeciesOption> speciesOptions;

}
