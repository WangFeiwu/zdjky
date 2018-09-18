package com.foxconn.service;

import com.foxconn.common.ServerResponse;
import com.foxconn.model.BloodPressures;
import com.foxconn.vo.GetParamVo;

/**
 * @Author: wfw
 * @Date: 2018/9/14 9:32
 */
public interface BloodPressuresService {

    ServerResponse insertSelective(BloodPressures bloodPressures);

    ServerResponse getListByDate(GetParamVo bloodPressuresVo);
}
