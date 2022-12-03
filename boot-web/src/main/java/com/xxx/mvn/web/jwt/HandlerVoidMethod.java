package com.xxx.mvn.web.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.ViewNameMethodReturnValueHandler;

/**
 * @author:TuoTuo
 * @createDate:2022/11/30 14:47
 * @description:
 */
@Slf4j
public class HandlerVoidMethod extends ViewNameMethodReturnValueHandler {
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        /*
         * 这里只处理了void返回值的方法，对于String返回值的方法则没有处理，原因是系统中可能还会用到springmvc的视图功能（例如jsp）
         * 如果说是前后分离的项目，springmvc层只提供纯接口的话，那么可以将下面代码全部删除，
         * 只写上一行  mavContainer.setRequestHandled(true);  即可
         */
        log.info("-----"+returnType.getParameterType());
        if (void.class == returnType.getParameterType()) {
            mavContainer.setRequestHandled(true);//这行代码是重点，它的作用是告诉其他组件本次请求已经被程序内部处理完毕，可以直接放行了
        } else {
            super.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
        }
    }
}
