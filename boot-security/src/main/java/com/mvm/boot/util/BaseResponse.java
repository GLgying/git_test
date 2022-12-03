package com.mvm.boot.util;

import java.util.Map;

/**
 * @author:TuoTuo
 * @createDate:2022/12/2 19:37
 * @description:
 */
public class BaseResponse<T> implements ResponseCodeInterface {
    private long code;
    private String msg;
    private T data;
    private Map obj;

    public static BaseResponse success(){
        return respMsg(RespEnum.SUCCESS,null,null);
    }

    public static BaseResponse success(String msg){
        RespEnum success = RespEnum.SUCCESS;
        success.setMsg(msg);
        return respMsg(success,null,null);
    }

    public static BaseResponse success(RespEnum respEnum){
        return respMsg(respEnum,null,null);
    }

    public static <T> BaseResponse success(RespEnum respEnum, T data){
        return respMsg(respEnum,data,null);
    }

    public static BaseResponse success( RespEnum respEnum,Map obj){
        return respMsg(respEnum,null,obj);
    }

    public static <T> BaseResponse success(RespEnum respEnum, T data, Map obj){
        return respMsg(respEnum,data,obj);
    }


    public static BaseResponse fail(){
        return respMsg(RespEnum.FAIL,null,null);
    }

    public static BaseResponse fail(String msg){
        RespEnum success = RespEnum.FAIL;
        success.setMsg(msg);
        return respMsg(success,null,null);
    }

    public static BaseResponse fail(RespEnum respEnum){
        return respMsg(respEnum,null,null);
    }

    public static <T> BaseResponse fail(RespEnum respEnum, T data){
        return respMsg(respEnum,data,null);
    }

    public static BaseResponse fail( RespEnum respEnum,Map obj){
        return respMsg(respEnum,null,obj);
    }

    public static <T> BaseResponse fail(RespEnum respEnum, T data, Map obj){
        return respMsg(respEnum,data,obj);
    }


    public static <T> BaseResponse respMsg(RespEnum respEnum, T data, Map obj){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(respEnum.getCode());
        baseResponse.setMsg(respEnum.getMsg());
        baseResponse.setData(data);
        baseResponse.setObj(obj);
        return baseResponse;
    }

    @Override
    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map getObj() {
        return obj;
    }

    public void setObj(Map obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", obj=" + obj +
                '}';
    }
}
