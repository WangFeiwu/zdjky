package com.foxconn.service.Impl;

import com.foxconn.common.ResponseCode;
import com.foxconn.common.ServerResponse;
import com.foxconn.mapper.BloodPressuresMapper;
import com.foxconn.model.BloodPressures;
import com.foxconn.service.BloodPressuresService;
import com.foxconn.utils.SignUtil;
import com.foxconn.vo.GetParamVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: wfw
 * @Date: 2018/9/14 9:33
 */
@Service
public class BloodPressuresServiceImpl implements BloodPressuresService {

    @Autowired
    private BloodPressuresMapper bloodPressuresMapper;

    @Override
    public ServerResponse insertSelective(BloodPressures bloodPressures) {
        if (bloodPressures!=null){
            if (StringUtils.isNotBlank(bloodPressures.getOrgCode())&&
                    StringUtils.isNotBlank(bloodPressures.getNonceStr())&&
                    StringUtils.isNotBlank(bloodPressures.getSign())&&
                    StringUtils.isNotBlank(bloodPressures.getSignType())&&
                    StringUtils.isNotBlank(bloodPressures.getAccountId())&&
                    bloodPressures.getSystolicPressure()!=null&&
                    bloodPressures.getDiastolicPressure()!=null&&
                    bloodPressures.getCreateTime()!=null
            ){
                bloodPressures.setCreateTime(new Date(bloodPressures.getCreateTime().getTime()*1000));
                String sign= SignUtil.buildSignStr(bloodPressures);
                if (sign.equals(bloodPressures.getSign())){
                    int resultCount=bloodPressuresMapper.insertSelective(bloodPressures);
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
    public ServerResponse getListByDate(GetParamVo bloodPressuresVo) {
        if (bloodPressuresVo!=null){
            if (StringUtils.isNotBlank(bloodPressuresVo.getOrgCode())&&
                    StringUtils.isNotBlank(bloodPressuresVo.getNonceStr())&&
                    StringUtils.isNotBlank(bloodPressuresVo.getSign())&&
                    StringUtils.isNotBlank(bloodPressuresVo.getSignType())&&
                    StringUtils.isNotBlank(bloodPressuresVo.getAccountId())&&
                    bloodPressuresVo.getCreateTimeStart()!=null&&
                    bloodPressuresVo.getCreateTimeEnd()!=null&&
                    bloodPressuresVo.getPageNum()!=null
                    ){
                bloodPressuresVo.setCreateTimeStart(new Date(bloodPressuresVo.getCreateTimeStart().getTime()*1000));
                bloodPressuresVo.setCreateTimeEnd(new Date(bloodPressuresVo.getCreateTimeEnd().getTime()*1000));
                String sign= SignUtil.buildSignStr(bloodPressuresVo);
                if (sign.equals(bloodPressuresVo.getSign())){
                    if (bloodPressuresVo.getCreateTimeStart().getTime()<=bloodPressuresVo.getCreateTimeEnd().getTime()){
                        List<BloodPressures> bloodPressuresList=bloodPressuresMapper.getListByDate(bloodPressuresVo.getAccountId(),
                                bloodPressuresVo.getCreateTimeStart(),bloodPressuresVo.getCreateTimeEnd(),bloodPressuresVo.getPageNum()*10);
                        return ServerResponse.createBySuccess("下载成功",bloodPressuresList);
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
