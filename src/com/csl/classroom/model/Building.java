package com.csl.classroom.model;

import lombok.Data;

/**
 * @author MaoLongLong
 * @date 2020-12-27 18:52
 */

/**
 * building
 */
@Data
public class Building {
    /**
     * id
     */
    private Integer id;

    /**
     * fullName
     */
    private String fullName;

    /**
     * aliasName
     */
    private String aliasName;

    /**
     * department
     */
    private String department;
}