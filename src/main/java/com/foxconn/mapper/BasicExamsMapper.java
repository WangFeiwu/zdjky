package com.foxconn.mapper;

import com.foxconn.model.BasicExams;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BasicExamsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BasicExams record);

    int insertSelective(BasicExams record);

    BasicExams selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasicExams record);

    int updateByPrimaryKey(BasicExams record);

    List<BasicExams> getListByDate(@Param("accountId") String accountId, @Param("createTimeStart") Date createTimeStart, @Param("createTimeEnd") Date createTimeEnd, @Param("pageNum") Integer pageNum);

}