package com.mvm.boot.util;

/**
 * @author:TuoTuo
 * @createDate:2022/12/2 20:06
 * @description:
 */
public interface ResponseCodeInterface {
    /**
     * 获取响应状态码
     * @return
     */
    long getCode();

    /**
     * 获取响应信息
     * @return
     */
    String getMsg();
}
