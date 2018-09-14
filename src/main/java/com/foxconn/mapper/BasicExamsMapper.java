package com.foxconn.mapper;

import com.foxconn.model.BasicExams;

public interface BasicExamsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BasicExams record);

    int insertSelective(BasicExams record);

    BasicExams selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasicExams record);

    int updateByPrimaryKey(BasicExams record);
}