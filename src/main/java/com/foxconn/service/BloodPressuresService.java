package com.foxconn.service;

import com.foxconn.model.BloodPressures;
import com.foxconn.vo.GetParamVo;

import java.util.Map;

/**
 * @Author: wfw
 * @Date: 2018/9/14 9:32
 */
public interface BloodPressuresService {

    Map insertSelective(BloodPressures bloodPressures);

    Map getListByDate(GetParamVo bloodPressuresVo);
}
