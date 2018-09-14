package com.foxconn.common;

/**
 * @Author: wfw
 * @Date: 2018/9/14 10:53
 */
public enum ResponseCode {
    SUCCESS(0,"上传成功！"),
    FAIL(10000001,"上传失败！"),
    NULL_PARAM(10000002,"上传失败！"),//没有参数
    NULL_KEY(10000003,"上传失败！"),//某些字段为空
    ERROR_SIGN(10000004,"上传失败！");//签名错误

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
