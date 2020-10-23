package com.lyn.juc.atomic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *  1. 创建线程的的方式三：实现Callable接口。相较于实现Runnable接口的方式方法可以有返回值，并且可以抛出异常
 *  2. 执行Callable方式，需要FutureTask实现类的支持，用于接收运算结果。FutureTask是Future接口的实现类
 */
public class TestCallableDemo {
    public static void main(String[] args) {
        CallableDemo callableDemo = new CallableDemo();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callableDemo);
        for (int i = 0; i < 3; i++) {
            new Thread(futureTask).start();
        }
        try {
            Integer result = futureTask.get();
            System.out.println(result);
            System.out.println("_____________");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class CallableDemo implements Callable<Integer>{
    public Integer call() throws Exception{
        int sum = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }
}
