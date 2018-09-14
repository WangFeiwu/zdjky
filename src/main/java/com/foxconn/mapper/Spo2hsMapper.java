package com.foxconn.mapper;

import com.foxconn.model.Spo2hs;

public interface Spo2hsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Spo2hs record);

    int insertSelective(Spo2hs record);

    Spo2hs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Spo2hs record);

    int updateByPrimaryKey(Spo2hs record);
}