package com.lyn.juc.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 定制含有阻塞队列的线程池
 */
public class TestHasBlockingQueueThreadPool {
    public static void main(String[] args) {

    }
}

class HasBlockingQueueExecutor{
    private ThreadPoolExecutor pool = null;
    public void init(){
        pool = new ThreadPoolExecutor(
                10,
                30,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new HasBlockingQueueThreadFactory(),
                new HasBlockingQueueRejectedExecutionHandler()
        );
    }
    public ExecutorService getThreadPool(){
        return this.pool;
    }

    public void destory(){
        if(pool != null){
            pool.shutdown();
        }
    }

    /**
     * 自定义ThreadFactory
     */
    private class HasBlockingQueueThreadFactory implements ThreadFactory{
        private AtomicInteger number = new AtomicInteger(0);
        public Thread newThread(Runnable r){
            Thread t = new Thread();
            String name = HasBlockingQueueThreadFactory.class.getSimpleName() + number.addAndGet(1);
            System.out.println("&&&&&&&&&&&&");
            System.out.println("线程名字： " + name);
            System.out.println("&&&&&&&&&&&&");
            return t;
        }

    }
    /**
     * 自定义RejectedExecutionHandler
     */
    private class HasBlockingQueueRejectedExecutionHandler implements RejectedExecutionHandler{
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("含有阻塞对立的线程池有问题了！！！！");
            try {
                // 核心改造点，由blockingqueue的offer改成put阻塞方法
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
