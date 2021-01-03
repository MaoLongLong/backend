package com.csl.classroom.mapper;

import com.csl.classroom.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author MaoLongLong
 * @date 2020-12-28 16:15
 */

@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findOneByUsername(@Param("username") String username);

    List<User> findAll();
}