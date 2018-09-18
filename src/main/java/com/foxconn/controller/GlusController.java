package com.foxconn.controller;

import com.foxconn.common.ServerResponse;
import com.foxconn.model.Glus;
import com.foxconn.service.GlusService;
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
public class GlusController {

    @Autowired
    private GlusService glusService;

    @RequestMapping(value = "/glus",method = RequestMethod.POST)
    public ServerResponse insertGlus(@RequestBody Glus glus){
        return glusService.insertSelective(glus);
    }

    @RequestMapping(value = "/glus/list",method = RequestMethod.POST)
    public ServerResponse getGlus(@RequestBody GetParamVo glusVo){
        return glusService.getListByDate(glusVo);
    }
}
