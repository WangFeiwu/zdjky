package com.foxconn.mapper;

import com.foxconn.model.Glus;

public interface GlusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Glus record);

    int insertSelective(Glus record);

    Glus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Glus record);

    int updateByPrimaryKey(Glus record);
}