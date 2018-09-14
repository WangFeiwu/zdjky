package com.foxconn.mapper;

import com.foxconn.model.BloodPressures;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: wfw
 * @Date: 2018/9/13 16:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BloodPressuresMapperTest {

    @Autowired
    private BloodPressuresMapper bloodPressuresMapper;

    @Test
    public void testSeleteById(){
        Integer id=1;
        BloodPressures bloodPressures=bloodPressuresMapper.selectByPrimaryKey(id);
        System.out.println(bloodPressures.getId());
    }

    @Test
    public void testInsert(){
        BloodPressures bloodPressures=new BloodPressures();
        bloodPressures.setAccountId("2123");
        bloodPressures.setAreaCode(1);
        bloodPressures.setCityCode(1);
        bloodPressures.setDiastolicPressure(1);
        bloodPressures.setLatitude(1);
        bloodPressures.setNonceStr("sds");
        bloodPressures.setLongitude(1);
        bloodPressures.setSignType("md5");
        int resultCount=bloodPressuresMapper.insert(bloodPressures);
        System.out.println(resultCount);
    }
}
