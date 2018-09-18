package com.foxconn.service.Impl;

import com.foxconn.common.ResponseCode;
import com.foxconn.common.ServerResponse;
import com.foxconn.mapper.HeartRatesMapper;
import com.foxconn.model.HeartRates;
import com.foxconn.service.HeartRatesService;
import com.foxconn.utils.SignUtil;
import com.foxconn.vo.GetParamVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: wfw
 * @Date: 2018/9/18 下午2:53
 */
@Service
public class HeartRatesServiceImpl implements HeartRatesService {

    @Autowired
    private HeartRatesMapper heartRatesMapper;

    @Override
    public ServerResponse insertSelective(HeartRates heartRates) {
        if (heartRates!=null){
            if (StringUtils.isNotBlank(heartRates.getOrgCode())&&
                    StringUtils.isNotBlank(heartRates.getNonceStr())&&
                    StringUtils.isNotBlank(heartRates.getSign())&&
                    StringUtils.isNotBlank(heartRates.getSignType())&&
                    StringUtils.isNotBlank(heartRates.getAccountId())&&
                    heartRates.getCreateTime()!=null
            ){
                heartRates.setCreateTime(new Date(heartRates.getCreateTime().getTime()*1000));
                String sign= SignUtil.buildSignStr(heartRates);
                if (sign.equals(heartRates.getSign())){
                    int resultCount=heartRatesMapper.insertSelective(heartRates);
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
    public ServerResponse getListByDate(GetParamVo heartRatesVo) {
        if (heartRatesVo!=null){
            if (StringUtils.isNotBlank(heartRatesVo.getOrgCode())&&
                    StringUtils.isNotBlank(heartRatesVo.getNonceStr())&&
                    StringUtils.isNotBlank(heartRatesVo.getSign())&&
                    StringUtils.isNotBlank(heartRatesVo.getSignType())&&
                    StringUtils.isNotBlank(heartRatesVo.getAccountId())&&
                    heartRatesVo.getCreateTimeStart()!=null&&
                    heartRatesVo.getCreateTimeEnd()!=null&&
                    heartRatesVo.getPageNum()!=null
            ){
                heartRatesVo.setCreateTimeStart(new Date(heartRatesVo.getCreateTimeStart().getTime()*1000));
                heartRatesVo.setCreateTimeEnd(new Date(heartRatesVo.getCreateTimeEnd().getTime()*1000));
                String sign= SignUtil.buildSignStr(heartRatesVo);
                if (sign.equals(heartRatesVo.getSign())){
                    if (heartRatesVo.getCreateTimeStart().getTime()<=heartRatesVo.getCreateTimeEnd().getTime()){
                        List<HeartRates> heartRatesList=heartRatesMapper.getListByDate(heartRatesVo.getAccountId(),
                                heartRatesVo.getCreateTimeStart(),heartRatesVo.getCreateTimeEnd(),heartRatesVo.getPageNum());
                        return ServerResponse.createBySuccess("下载成功",heartRatesList);
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
