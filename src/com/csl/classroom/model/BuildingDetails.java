package com.csl.classroom.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author MaoLongLong
 * @date 2020-12-26 17:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BuildingDetails extends Building {

    private Integer currentFreeClassrooms = 0;

    private Integer totalNumberOfClassrooms = 0;

    private Integer freeCapacity = 0;

    private Integer totalCapacity = 0;
}
