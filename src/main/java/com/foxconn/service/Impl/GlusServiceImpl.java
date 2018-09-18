package com.foxconn.service.Impl;

import com.foxconn.common.ResponseCode;
import com.foxconn.common.ServerResponse;
import com.foxconn.mapper.GlusMapper;
import com.foxconn.model.Glus;
import com.foxconn.service.GlusService;
import com.foxconn.utils.SignUtil;
import com.foxconn.vo.GetParamVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: wfw
 * @Date: 2018/9/18 下午2:47
 */
@Service
public class GlusServiceImpl implements GlusService {

    @Autowired
    private GlusMapper glusMapper;

    @Override
    public ServerResponse insertSelective(Glus glus) {
        if (glus!=null){
            if (StringUtils.isNotBlank(glus.getOrgCode())&&
                    StringUtils.isNotBlank(glus.getNonceStr())&&
                    StringUtils.isNotBlank(glus.getSign())&&
                    StringUtils.isNotBlank(glus.getSignType())&&
                    StringUtils.isNotBlank(glus.getAccountId())&&
                    glus.getGlu()!=null&&
                    glus.getCreateTime()!=null
            ){
                glus.setCreateTime(new Date(glus.getCreateTime().getTime()*1000));
                String sign= SignUtil.buildSignStr(glus);
                if (sign.equals(glus.getSign())){
                    int resultCount=glusMapper.insertSelective(glus);
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
    public ServerResponse getListByDate(GetParamVo glusVo) {
        if (glusVo!=null){
            if (StringUtils.isNotBlank(glusVo.getOrgCode())&&
                    StringUtils.isNotBlank(glusVo.getNonceStr())&&
                    StringUtils.isNotBlank(glusVo.getSign())&&
                    StringUtils.isNotBlank(glusVo.getSignType())&&
                    StringUtils.isNotBlank(glusVo.getAccountId())&&
                    glusVo.getCreateTimeStart()!=null&&
                    glusVo.getCreateTimeEnd()!=null&&
                    glusVo.getPageNum()!=null
            ){
                glusVo.setCreateTimeStart(new Date(glusVo.getCreateTimeStart().getTime()*1000));
                glusVo.setCreateTimeEnd(new Date(glusVo.getCreateTimeEnd().getTime()*1000));
                String sign= SignUtil.buildSignStr(glusVo);
                if (sign.equals(glusVo.getSign())){
                    if (glusVo.getCreateTimeStart().getTime()<=glusVo.getCreateTimeEnd().getTime()){
                        List<Glus> glusList=glusMapper.getListByDate(glusVo.getAccountId(),
                                glusVo.getCreateTimeStart(),glusVo.getCreateTimeEnd(),glusVo.getPageNum()*10);
                        return ServerResponse.createBySuccess("下载成功",glusList);
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
