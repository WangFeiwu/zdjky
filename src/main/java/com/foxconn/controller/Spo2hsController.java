package com.foxconn.controller;

import com.foxconn.common.ServerResponse;
import com.foxconn.model.Spo2hs;
import com.foxconn.service.Spo2hsService;
import com.foxconn.vo.GetParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wfw
 * @Date: 2018/9/18 下午3:07
 */
@RestController
public class Spo2hsController {

    @Autowired
    private Spo2hsService spo2hsService;

    @RequestMapping(value = "/spo2hs",method = RequestMethod.POST)
    public ServerResponse insertSpo2hs(@RequestBody Spo2hs spo2hs){
        return spo2hsService.insertSelective(spo2hs);
    }

    @RequestMapping(value = "/spo2hs/list",method = RequestMethod.POST)
    public ServerResponse getSpo2hs(@RequestBody GetParamVo spo2hsVo){
        return spo2hsService.getListByDate(spo2hsVo);
    }
}
