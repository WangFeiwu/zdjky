package com.foxconn.controller;

import com.foxconn.common.ServerResponse;
import com.foxconn.model.BasicExams;
import com.foxconn.service.BasicExamsService;
import com.foxconn.vo.GetParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicExamsController {

    @Autowired
    private BasicExamsService basicExamsService;

    @RequestMapping(value = "/basicExams",method = RequestMethod.POST)
    public ServerResponse insertBasicExams(@RequestBody BasicExams basicExams){
        return basicExamsService.insertSelective(basicExams);
    }

    @RequestMapping(value = "/basicExams/list",method = RequestMethod.POST)
    public ServerResponse getBasicExams(@RequestBody GetParamVo basicExamsVo){
        return basicExamsService.getListByDate(basicExamsVo);
    }
}
