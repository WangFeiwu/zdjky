package com.foxconn.service.Impl;

import com.foxconn.common.ResponseCode;
import com.foxconn.mapper.BloodPressuresMapper;
import com.foxconn.model.BloodPressures;
import com.foxconn.service.BloodPressuresService;
import com.foxconn.utils.SignUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wfw
 * @Date: 2018/9/14 9:33
 */
@Service
public class BloodPressuresServiceImpl implements BloodPressuresService {

    @Autowired
    private BloodPressuresMapper bloodPressuresMapper;

/*
    @Override
    public BloodPressures getById(Integer id) {
        return bloodPressuresMapper.selectByPrimaryKey(id);
    }
*/

    @Override
    public Map insertSelective(BloodPressures bloodPressures) {
        Map<String,Object> map=new HashMap<>();
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
                String sign= SignUtil.buildSignStr(bloodPressures);
                if (sign.equals(bloodPressures.getSign())){
                    int resultCount=bloodPressuresMapper.insertSelective(bloodPressures);
                    if (resultCount>0){
                        map.put("returnCode", "SUCCESS");
                        map.put("returnStr", ResponseCode.SUCCESS.getReturnStr());
                    }else {
                        map.put("returnCode", "FAIL");
                        map.put("returnStr", ResponseCode.FAIL.getReturnStr());
                        map.put("errCode", ResponseCode.FAIL.getStatus());
                    }
                }
                else {
                    map.put("returnCode", "FAIL");
                    map.put("returnStr", ResponseCode.ERROR_SIGN.getReturnStr());
                    map.put("errCode", ResponseCode.ERROR_SIGN.getStatus());
                }

            }else {
                map.put("returnCode", "FAIL");
                map.put("returnStr", ResponseCode.NULL_KEY.getReturnStr());
                map.put("errCode", ResponseCode.NULL_KEY.getStatus());
            }
        }else {
            map.put("returnCode", "FAIL");
            map.put("returnStr", ResponseCode.NULL_PARAM.getReturnStr());
            map.put("errCode", ResponseCode.NULL_PARAM.getStatus());
        }
        return map;
    }
}
