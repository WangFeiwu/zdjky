package com.foxconn.service;

import com.foxconn.common.ServerResponse;
import com.foxconn.model.BasicExams;
import com.foxconn.vo.GetParamVo;

public interface BasicExamsService {
    ServerResponse insertSelective(BasicExams basicExams);

    ServerResponse getListByDate(GetParamVo basicExamsVo);
}
