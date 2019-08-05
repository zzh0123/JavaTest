package com.javatest.proxytest;

import com.javatest.MonitorUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Author: zzhh
 * Date: 2019/8/5 12:07
 * Description: ${DESCRIPTION}
 */
public class StudentInvocationHandler<T> implements InvocationHandler {
    // invocationHandler持有的被代理对象
    private T target;

    public StudentInvocationHandler(T target){
        this.target = target;
    }

     /**
      * @method  代表正在执行的方法
      * @description 描述一下方法的作用
      * @date:  
      * @author:
      * @param proxy 代表动态代理对象
      *  @param args 代表调用目标方法时传入的实参
      * @return 
      */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理执行" +method.getName() + "方法");
        //代理过程中插入监测方法,计算该方法耗时
        MonitorUtil.start();
        Object result = method.invoke(target, args);
        MonitorUtil.finish(method.getName());
        return result;
    }
}
