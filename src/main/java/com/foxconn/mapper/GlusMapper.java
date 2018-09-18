package com.foxconn.mapper;

import com.foxconn.model.Glus;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface GlusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Glus record);

    int insertSelective(Glus record);

    Glus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Glus record);

    int updateByPrimaryKey(Glus record);

    List<Glus> getListByDate(@Param("accountId") String accountId, @Param("createTimeStart") Date createTimeStart, @Param("createTimeEnd") Date createTimeEnd, @Param("pageNum") Integer pageNum);

}