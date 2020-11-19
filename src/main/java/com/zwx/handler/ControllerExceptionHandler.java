package com.zwx.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 拦截异常至自定义错误页面（统一管理异常）
 * @author coderZWX
 * @date 2020-11-04 14:39
 */
@ControllerAdvice//注解用于拦截所有Controller
public class ControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)//注解表示拦截所有Exception异常处理
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
        //日志记录异常
        logger.error("Request URL : {} , Exception : {}",request.getRequestURL(),e);

        //如果异常存在状态的声明就抛出异常交给springboot处理
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }

        //记录请求和路径并返回到自定义error页面
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        return mv;
    }

}
