package com.foxconn.service.Impl;

import com.foxconn.common.ResponseCode2;
import com.foxconn.common.ServerResponse;
import com.foxconn.model.BasicExams;
import com.foxconn.service.BasicExamsService;
import com.foxconn.utils.SignUtil;
import com.foxconn.vo.GetParamVo;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class BasicExamsServiceImpl implements BasicExamsService {
    @Override
    public ServerResponse insertSelective(BasicExams basicExams) {
        if (basicExams!=null){
            if (StringUtils.isNoneBlank(basicExams.getOrgCode())&&
                    StringUtils.isNoneBlank(basicExams.getNonceStr())&&
                    StringUtils.isNoneBlank(basicExams.getSign())&&
                    StringUtils.isNoneBlank(basicExams.getSignType())&&
                    StringUtils.isNoneBlank(basicExams.getAccountId())&&
                    basicExams.getHeight()!=null&&
                    basicExams.getWeight()!=null&&
                    basicExams.getMuscleRate()!=null&&
                    basicExams.getWaterRate()!=null&&
                    basicExams.getCreateTime()!=null){
                basicExams.setCreateTime(new Date(basicExams.getCreateTime().getTime()*1000));
                String sign= SignUtil.buildSignStr(basicExams);
            }else {
                return ServerResponse.createByError(ResponseCode2.LACK_KEY.getReturnCode(),"上传失败！",ResponseCode2.LACK_KEY.getStatusCode());
            }
        }
        return ServerResponse.createByError(ResponseCode2.NULL_PARAM.getReturnCode(),"上传失败！",ResponseCode2.NULL_PARAM.getStatusCode());
    }

    @Override
    public ServerResponse getListByDate(GetParamVo basicExamsVo) {
        return null;
    }
}
