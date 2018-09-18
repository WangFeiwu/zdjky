package com.foxconn.service;

import com.foxconn.common.ServerResponse;
import com.foxconn.model.Glus;
import com.foxconn.vo.GetParamVo;

/**
 * @Author: wfw
 * @Date: 2018/9/18 下午2:44
 */
public interface GlusService {

    ServerResponse insertSelective(Glus glus);

    ServerResponse getListByDate(GetParamVo glusVo);
}
