package com.foxconn.service.Impl;

import com.foxconn.common.ResponseCode;
import com.foxconn.common.ServerResponse;
import com.foxconn.mapper.BasicExamsMapper;
import com.foxconn.model.BasicExams;
import com.foxconn.service.BasicExamsService;
import com.foxconn.utils.SignUtil;
import com.foxconn.vo.GetParamVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BasicExamsServiceImpl implements BasicExamsService {

    @Autowired
    private BasicExamsMapper basicExamsMapper;

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
                if (sign.equals(basicExams.getSign())){
                    int resultCount=basicExamsMapper.insertSelective(basicExams);
                    if (resultCount>0){
                        return ServerResponse.createBySuccess("上传成功！");
                    }else {
                        return ServerResponse.createByError(ResponseCode.FAIL.getReturnCode(),"上传失败！", ResponseCode.FAIL.getStatusCode());
                    }
                }else {
                    return ServerResponse.createByError(ResponseCode.ERROR_SIGN.getReturnCode(),"上传失败！", ResponseCode.ERROR_SIGN.getStatusCode());
                }
            }else {
                return ServerResponse.createByError(ResponseCode.LACK_KEY.getReturnCode(),"上传失败！", ResponseCode.LACK_KEY.getStatusCode());
            }
        }
        return ServerResponse.createByError(ResponseCode.NULL_PARAM.getReturnCode(),"上传失败！", ResponseCode.NULL_PARAM.getStatusCode());
    }

    @Override
    public ServerResponse getListByDate(GetParamVo basicExamsVo) {
        if (basicExamsVo!=null){
            if (StringUtils.isNotBlank(basicExamsVo.getOrgCode())&&
                    StringUtils.isNotBlank(basicExamsVo.getNonceStr())&&
                    StringUtils.isNotBlank(basicExamsVo.getSign())&&
                    StringUtils.isNotBlank(basicExamsVo.getSignType())&&
                    StringUtils.isNotBlank(basicExamsVo.getAccountId())&&
                    basicExamsVo.getCreateTimeStart()!=null&&
                    basicExamsVo.getCreateTimeEnd()!=null&&
                    basicExamsVo.getPageNum()!=null
            ){
                basicExamsVo.setCreateTimeStart(new Date(basicExamsVo.getCreateTimeStart().getTime()*1000));
                basicExamsVo.setCreateTimeEnd(new Date(basicExamsVo.getCreateTimeEnd().getTime()*1000));
                String sign= SignUtil.buildSignStr(basicExamsVo);
                if (sign.equals(basicExamsVo.getSign())){
                    if (basicExamsVo.getCreateTimeStart().getTime()<=basicExamsVo.getCreateTimeEnd().getTime()){
                        List<BasicExams> basicExamsList=basicExamsMapper.getListByDate(basicExamsVo.getAccountId(),
                                basicExamsVo.getCreateTimeStart(),basicExamsVo.getCreateTimeEnd(),basicExamsVo.getPageNum()*10);
                        return ServerResponse.createBySuccess("下载成功",basicExamsList);
                    }else {
                        return ServerResponse.createByError(ResponseCode.ERROR_ENDTIME.getReturnCode(),"下载失败！", ResponseCode.ERROR_ENDTIME.getStatusCode());
                    }
                }else {
                    return ServerResponse.createByError(ResponseCode.ERROR_SIGN.getReturnCode(),"下载失败！", ResponseCode.ERROR_SIGN.getStatusCode());
                }
            }else {
                return ServerResponse.createByError(ResponseCode.LACK_KEY.getReturnCode(),"下载失败！", ResponseCode.LACK_KEY.getStatusCode());
            }
        }
        return ServerResponse.createByError(ResponseCode.NULL_PARAM.getReturnCode(),"下载失败！", ResponseCode.NULL_PARAM.getStatusCode());
    }
}
