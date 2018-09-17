package com.foxconn.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> implements Serializable {
    private String returnCode;
    private String returnStr;
    private int errCode;
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

    private ServerResponse(String returnCode, String returnStr, int errCode) {
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

    public int getErrCode() {
        return errCode;
    }

    public T getData() {
        return data;
    }

    public static <T> ServerResponse<T> createBySuccess(String returnStr){
        return new ServerResponse<T>(ResponseCode2.SUCCESS.getReturnCode(),returnStr);
    }

    public static <T> ServerResponse<T> createBySuccess(String returnStr,T data){
        return new ServerResponse<T>(ResponseCode2.SUCCESS.getReturnCode(),returnStr,data);
    }

    public static <T> ServerResponse<T> createByError(String returnCode,String returnStr,int errCode){
        return new ServerResponse<T>(returnCode,returnStr,errCode);
    }
}