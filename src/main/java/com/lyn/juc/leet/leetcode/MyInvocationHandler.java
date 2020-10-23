package com.lyn.juc.leet.leetcode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2020-03-29 21:15
 **/
public class MyInvocationHandler implements InvocationHandler {
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进行动态代理");
        return method.invoke(target, args);
    }
}
