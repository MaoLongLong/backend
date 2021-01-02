package com.csl.classroom.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author MaoLongLong
 * @date 2020-12-26 22:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ClassroomDetails extends Classroom {
    private String buildingName;
}
