package com.lyn.juc.jmm.myThreadPool;

import java.util.concurrent.*;

/**
 * @program: projects
 * @description: 线程池
 * @author: lyn
 * * @create: 2019-04-16 14:03
 **/
// 自定义线程池
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                3,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                // 抛出异常的RejectedExecutionHandler
//                new ThreadPoolExecutor.AbortPolicy()
                // 将任务交给调用者进行处理
                new ThreadPoolExecutor.CallerRunsPolicy()
                // 丢掉最久的任务
//                new ThreadPoolExecutor.DiscardOldestPolicy()
                // 丢掉任务
//                new ThreadPoolExecutor.DiscardPolicy()
        );

        try {
            for (int i = 1; i <= 10; i++) {
                final int temp = i;
                executorService.execute(() -> {
                    try {
                TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t办理业务\t" + temp);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }
}
