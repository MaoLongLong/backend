package com.csl.classroom.mapper;

import com.csl.classroom.model.Building;
import com.csl.classroom.model.BuildingDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author MaoLongLong
 * @date 2020-12-27 18:52
 */

@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
public interface BuildingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Building record);

    int insertSelective(Building record);

    Building selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Building record);

    int updateByPrimaryKey(Building record);

    List<Building> findAll(@Param("keyword") String keyword);

    BuildingDetails getBuildingDetail(Integer id);

    Integer count();
}