package com.csl.classroom.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author MaoLongLong
 * @date 2021-01-05 14:00
 */
@Data
@Builder
public class Dashboard {

    private Integer userCount;

    private Integer buildingCount;

    private Integer classroomCount;
}
