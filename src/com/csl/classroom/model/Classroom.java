package com.csl.classroom.model;

import lombok.Data;

/**
 * @author MaoLongLong
 * @date 2020-12-27 18:52
 */

/**
 * classroom
 */
@Data
public class Classroom {
    /**
     * id
     */
    private Integer id;

    /**
     * name
     */
    private String name;

    /**
     * capacity
     */
    private Integer capacity;

    /**
     * buildingId
     */
    private Integer buildingId;

    /**
     * status
     */
    private Boolean status;
}