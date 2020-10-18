package com.robot.api.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * species
 * @author robot
 */
@Data
public class Species implements Serializable {
    private Integer id;

    private String name;

    private Byte sort;

    private String coverImg;

    private List<SpeciesOption> speciesOptionList;

    private static final long serialVersionUID = 1L;
}