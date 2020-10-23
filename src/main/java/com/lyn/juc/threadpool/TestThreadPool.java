package com.lyn.juc.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestThreadPool {
    public static void main(String[] args) {
        CustomerThreadPoolExecuctor poolExecuctor = new CustomerThreadPoolExecuctor();
        poolExecuctor.init();
        ExecutorService executor = poolExecuctor.getCustomerThreadPoolExecutor();
        for (int i = 0; i < 100; i++) {
            System.out.println("提交第 " + i + " 个任务!");
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("runninng=========");
                }
            });
        }
    }
}

class CustomerThreadPoolExecuctor{
    private ThreadPoolExecutor pool = null;

    /**
     * 线程池的初始化操作：
     *
     */
    public void init(){
        pool = new ThreadPoolExecutor(30,
                30,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                new CustomerThreadFactory(),
                new CustomerRejectedExecutionHandler());
    }

    public ExecutorService getCustomerThreadPoolExecutor(){
        return this.pool;
    }

    // 关闭当前的线程池
    public void destory(){
        if(pool != null){
            pool.shutdown();
        }
    }

    /**
     * 自定义内部类的线程工厂
     */
    private class CustomerThreadFactory implements ThreadFactory{
        private AtomicInteger count = new AtomicInteger(0);
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = CustomerThreadFactory.class.getSimpleName() + count.addAndGet(1);
            System.out.println("######: " + threadName);
            return t;
        }
    }
    /**
     * 自定义RejectedExecutionHandler
     */
    private class CustomerRejectedExecutionHandler implements RejectedExecutionHandler{

        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            // 记录异常
            // 报警处理
            System.out.println("*************************");
            System.out.println("******* 发生错误了 ******");
            System.out.println("*************************");
        }
    }


}
