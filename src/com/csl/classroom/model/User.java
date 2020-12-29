package com.csl.classroom.model;

import lombok.Data;

/**
 * @author MaoLongLong
 * @date 2020-12-28 16:15
 */

/**
 * user
 */
@Data
public class User {
    /**
     * id
     */
    private Integer id;

    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private String password;

    /**
     * avatar
     */
    private String avatar;

    /**
     * nickname
     */
    private String nickname;
}