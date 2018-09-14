package com.foxconn.mapper;

import com.foxconn.model.HeartRates;

public interface HeartRatesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HeartRates record);

    int insertSelective(HeartRates record);

    HeartRates selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HeartRates record);

    int updateByPrimaryKey(HeartRates record);
}