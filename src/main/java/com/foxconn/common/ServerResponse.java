package com.foxconn.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> implements Serializable {
    private String returnCode;
    private String returnStr;
    private Integer errCode;
    private T data;

    private ServerResponse(String returnCode, String returnStr) {
        this.returnCode = returnCode;
        this.returnStr = returnStr;
    }

    private ServerResponse(String returnCode, String returnStr, T data) {
        this.returnCode = returnCode;
        this.returnStr = returnStr;
        this.data = data;
    }

    private ServerResponse(String returnCode, String returnStr, Integer errCode) {
        this.returnCode = returnCode;
        this.returnStr = returnStr;
        this.errCode = errCode;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public String getReturnStr() {
        return returnStr;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public T getData() {
        return data;
    }

    public static <T> ServerResponse<T> createBySuccess(String returnStr){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getReturnCode(),returnStr);
    }

    public static <T> ServerResponse<T> createBySuccess(String returnStr,T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getReturnCode(),returnStr,data);
    }

    public static <T> ServerResponse<T> createByError(String returnCode,String returnStr,Integer errCode){
        return new ServerResponse<T>(returnCode,returnStr,errCode);
    }
}
