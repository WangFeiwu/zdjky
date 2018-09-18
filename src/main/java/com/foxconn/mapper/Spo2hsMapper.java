package com.foxconn.mapper;

import com.foxconn.model.Spo2hs;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface Spo2hsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Spo2hs record);

    int insertSelective(Spo2hs record);

    Spo2hs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Spo2hs record);

    int updateByPrimaryKey(Spo2hs record);

    List<Spo2hs> getListByDate(@Param("accountId") String accountId, @Param("createTimeStart") Date createTimeStart, @Param("createTimeEnd") Date createTimeEnd, @Param("pageNum") Integer pageNum);

}