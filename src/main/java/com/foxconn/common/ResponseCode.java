package com.foxconn.common;

/**
 * @Author: wfw
 * @Date: 2018/9/14 10:53
 */
public enum ResponseCode {
    SUCCESS(0,"成功！"),
    FAIL(10000001,"失败！"),
    NULL_PARAM(10000002,"失败！"),//没有参数
    NULL_KEY(10000003,"失败！"),//某些字段为空
    ERROR_SIGN(10000004,"失败！"),//签名错误
    ERROR_ENDTIME(10000005,"失败！");//结束时间错误

    private int status;
    private String returnStr;

    ResponseCode(int status, String returnStr) {
        this.status = status;
        this.returnStr = returnStr;
    }

    public int getStatus() {
        return status;
    }

    public String getReturnStr() {
        return returnStr;
    }
}
