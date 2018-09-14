package com.foxconn.mapper;

import com.foxconn.model.BloodPressures;

public interface BloodPressuresMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BloodPressures record);

    int insertSelective(BloodPressures record);

    BloodPressures selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BloodPressures record);

    int updateByPrimaryKey(BloodPressures record);
}