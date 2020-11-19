package com.zwx.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 切面生成访问日志
 * @author coderZWX
 * @date 2020-11-04 15:25
 */
@Aspect
@Component
public class LogAop {

    //配置切面,controller包下的所有类所有方法
    @Pointcut("execution(* com.zwx.controller.*.*(..))")
    public void log(){}

    //前置通知
    @Before("log()")
    public void doBefore(){

    }

    //后置通知
    @After("log()")
    public void doAfter(){

    }

}
