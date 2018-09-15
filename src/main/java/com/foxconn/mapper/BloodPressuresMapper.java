package com.foxconn.mapper;

import com.foxconn.model.BloodPressures;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BloodPressuresMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BloodPressures record);

    int insertSelective(BloodPressures record);

    BloodPressures selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BloodPressures record);

    int updateByPrimaryKey(BloodPressures record);

    List<BloodPressures> getListByDate(@Param("accountId") String accountId, @Param("createTimeStart") Date createTimeStart, @Param("createTimeEnd") Date createTimeEnd, @Param("pageNum") Integer pageNum);
}