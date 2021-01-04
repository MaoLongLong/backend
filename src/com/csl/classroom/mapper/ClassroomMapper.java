package com.csl.classroom.mapper;

import com.csl.classroom.model.Classroom;
import com.csl.classroom.model.ClassroomDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author MaoLongLong
 * @date 2020-12-27 18:52
 */

@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
public interface ClassroomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Classroom record);

    int insertSelective(Classroom record);

    Classroom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Classroom record);

    int updateByPrimaryKey(Classroom record);

    List<ClassroomDetails> findAll(@Param("keyword") String keyword);

    int deleteByBuildingId(@Param("buildingId")Integer buildingId);


}