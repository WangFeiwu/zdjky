package com.foxconn.service;

import com.foxconn.model.BloodPressures;
import com.foxconn.vo.BloodPressuresVo;

import java.util.Map;

/**
 * @Author: wfw
 * @Date: 2018/9/14 9:32
 */
public interface BloodPressuresService {
//    BloodPressures getById(Integer id);

    Map insertSelective(BloodPressures bloodPressures);

    Map getListByDate(BloodPressuresVo bloodPressuresVo);
}
