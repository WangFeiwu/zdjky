package com.foxconn.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: wfw
 * @Date: 2018/9/14 11:20
 */
public class SignUtil {
    private static final Logger logger=LoggerFactory.getLogger(SignUtil.class);

    public static String buildSignStr(Object obj) {
        Map<String, Object> params=transBean2Map(obj);
        StringBuilder sb = new StringBuilder();
        // 将参数以参数名的字典升序排序
        Map<String, Object> sortParams = new TreeMap<String, Object>(params);
        // 遍历排序的字典,并拼接"key=value"格式
        for (Map.Entry<String, Object> entry : sortParams.entrySet()) {
            if (!entry.getKey().equals("id")&&!entry.getKey().equals("sign")&&!entry.getKey().equals("signType")){
                if (sb.length()!=0) {
                    sb.append("&");
                }
                if (entry.getValue() instanceof Date){
                    sb.append(entry.getKey()).append("=").append(((Date) entry.getValue()).getTime());
                }else {
                    sb.append(entry.getKey()).append("=").append(entry.getValue()==null?"":entry.getValue());
                }
            }

        }
        if (sb.length()!=0){
            //key值
            sb.append("&key=2pSl0IqzsjFygg9uMZwIfQQTJL7UJOVw");
            String sign=string2MD5(sb.toString()).toUpperCase();
            return sign;
//            return sb.toString();
        }else {
            return null;
        }
    }

    public static Map<String, Object> transBean2Map(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            logger.error("transBean2Map Error");
        }
        return map;
    }

    public static String string2MD5(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] hash = md.digest();
            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException");
        }
        return hexString.toString();
    }

}
