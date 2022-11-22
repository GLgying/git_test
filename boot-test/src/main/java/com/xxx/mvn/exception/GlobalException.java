package com.xxx.mvn.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String exception(Exception e){
        return "系统异常...";
    }
}
