package com.foxconn.controller;

import com.foxconn.common.ServerResponse;
import com.foxconn.model.HeartRates;
import com.foxconn.service.HeartRatesService;
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
public class HeartRatesController {

    @Autowired
    private HeartRatesService heartRatesService;

    @RequestMapping(value = "/heartRates",method = RequestMethod.POST)
    public ServerResponse insertHeartRates(@RequestBody HeartRates heartRates){
        return heartRatesService.insertSelective(heartRates);
    }

    @RequestMapping(value = "/heartRates/list",method = RequestMethod.POST)
    public ServerResponse getHeartRates(@RequestBody GetParamVo heartRatesVo){
        return heartRatesService.getListByDate(heartRatesVo);
    }
}
