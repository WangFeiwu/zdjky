package com.foxconn.mapper;

import com.foxconn.model.HeartRates;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface HeartRatesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HeartRates record);

    int insertSelective(HeartRates record);

    HeartRates selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HeartRates record);

    int updateByPrimaryKey(HeartRates record);

    List<HeartRates> getListByDate(@Param("accountId") String accountId, @Param("createTimeStart") Date createTimeStart, @Param("createTimeEnd") Date createTimeEnd, @Param("pageNum") Integer pageNum);

}