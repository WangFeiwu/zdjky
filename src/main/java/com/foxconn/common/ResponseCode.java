package com.foxconn.common;

/**
 * @Author: wfw
 * @Date: 2018/9/14 10:53
 */
public enum ResponseCode {
    SUCCESS(0,"SUCCESS"),
    FAIL(10000001,"FAIL"),
    NULL_PARAM(10000002,"FAIL"),//没有参数
    LACK_KEY(10000003,"FAIL"),//某些字段为空
    ERROR_SIGN(10000004,"FAIL"),//签名错误
    ERROR_ENDTIME(10000005,"FAIL");//结束时间错误

    private int statusCode;
    private String returnCode;

    ResponseCode(int statusCode, String returnCode) {
        this.statusCode = statusCode;
        this.returnCode = returnCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getReturnCode() {
        return returnCode;
    }
}
