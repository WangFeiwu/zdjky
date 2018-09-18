package com.foxconn.service;

import com.foxconn.common.ServerResponse;
import com.foxconn.model.Spo2hs;
import com.foxconn.vo.GetParamVo;

/**
 * @Author: wfw
 * @Date: 2018/9/18 下午2:44
 */
public interface Spo2hsService {

    ServerResponse insertSelective(Spo2hs spo2hs);

    ServerResponse getListByDate(GetParamVo spo2hsVo);
}
