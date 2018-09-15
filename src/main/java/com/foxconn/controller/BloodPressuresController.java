package com.foxconn.controller;

import com.foxconn.model.BloodPressures;
import com.foxconn.service.BloodPressuresService;
import com.foxconn.vo.BloodPressuresVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @Author: wfw
 * @Date: 2018/9/14 9:34
 */
@RestController
public class BloodPressuresController {

    @Autowired
    private BloodPressuresService bloodPressuresService;

/*
    @RequestMapping("/bloodPressures/{id}")
    public BloodPressures getById(@PathVariable("id") Integer id){
        return bloodPressuresService.getById(id);
    }
*/

    @RequestMapping(value = "/bloodPressures",method = RequestMethod.POST)
    public Map insertBloodPressures(@RequestBody BloodPressures bloodPressures){
        return bloodPressuresService.insertSelective(bloodPressures);
    }

    @RequestMapping(value = "/bloodPressures/list",method = RequestMethod.POST)
    public Map getBloodPressures(@RequestBody BloodPressuresVo bloodPressuresVo){
        return bloodPressuresService.getListByDate(bloodPressuresVo);
    }

}
