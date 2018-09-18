package com.foxconn.service.Impl;

import com.foxconn.common.ResponseCode;
import com.foxconn.common.ServerResponse;
import com.foxconn.mapper.Spo2hsMapper;
import com.foxconn.model.Spo2hs;
import com.foxconn.service.Spo2hsService;
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
public class Spo2hsServiceImpl implements Spo2hsService {

    @Autowired
    private Spo2hsMapper spo2hsMapper;

    @Override
    public ServerResponse insertSelective(Spo2hs spo2hs) {
        if (spo2hs!=null){
            if (StringUtils.isNotBlank(spo2hs.getOrgCode())&&
                    StringUtils.isNotBlank(spo2hs.getNonceStr())&&
                    StringUtils.isNotBlank(spo2hs.getSign())&&
                    StringUtils.isNotBlank(spo2hs.getSignType())&&
                    StringUtils.isNotBlank(spo2hs.getAccountId())&&
                    spo2hs.getSpo2h()!=null&&
                    spo2hs.getCreateTime()!=null
            ){
                spo2hs.setCreateTime(new Date(spo2hs.getCreateTime().getTime()*1000));
                String sign= SignUtil.buildSignStr(spo2hs);
                if (sign.equals(spo2hs.getSign())){
                    int resultCount=spo2hsMapper.insertSelective(spo2hs);
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
    public ServerResponse getListByDate(GetParamVo spo2hsVo) {
        if (spo2hsVo!=null){
            if (StringUtils.isNotBlank(spo2hsVo.getOrgCode())&&
                    StringUtils.isNotBlank(spo2hsVo.getNonceStr())&&
                    StringUtils.isNotBlank(spo2hsVo.getSign())&&
                    StringUtils.isNotBlank(spo2hsVo.getSignType())&&
                    StringUtils.isNotBlank(spo2hsVo.getAccountId())&&
                    spo2hsVo.getCreateTimeStart()!=null&&
                    spo2hsVo.getCreateTimeEnd()!=null&&
                    spo2hsVo.getPageNum()!=null
            ){
                spo2hsVo.setCreateTimeStart(new Date(spo2hsVo.getCreateTimeStart().getTime()*1000));
                spo2hsVo.setCreateTimeEnd(new Date(spo2hsVo.getCreateTimeEnd().getTime()*1000));
                String sign= SignUtil.buildSignStr(spo2hsVo);
                if (sign.equals(spo2hsVo.getSign())){
                    if (spo2hsVo.getCreateTimeStart().getTime()<=spo2hsVo.getCreateTimeEnd().getTime()){
                        List<Spo2hs> spo2hsList=spo2hsMapper.getListByDate(spo2hsVo.getAccountId(),
                                spo2hsVo.getCreateTimeStart(),spo2hsVo.getCreateTimeEnd(),spo2hsVo.getPageNum()*10);
                        return ServerResponse.createBySuccess("下载成功",spo2hsList);
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
