package com.lyn.juc.jmm.threadCondition;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 传统方式的生产者和消费者模式
 *
 * @program: projects
 * @author: lyn
 * * @create: 2019-12-13 14:52
 **/
public class TranProductAndConsumer {
    public static void main(String[] args) {
        lockWeap();
    }

    public static void lockWeap() {
        TranProCon proCon = new TranProCon();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                proCon.increment();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "生产者1111").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                proCon.increment();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "生产者2222").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                proCon.decrement();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者AAA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                proCon.decrement();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者BBB").start();
    }

    public static void trasation() {
        TranProCon proCon = new TranProCon();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                proCon.product();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "生产者").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                proCon.consumer();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者AAA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                proCon.consumer();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者BBB").start();
    }

    public static void lock() {
        TranProCon proCon = new TranProCon();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                proCon.lockProduct();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "生产者").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                proCon.lockConsumer();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者AAA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                proCon.consumer();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者BBB").start();
    }
}

class TranProCon {
    private static Integer goods = 0;

    // 生产产品
    public synchronized void product() {
        // 当产品为0，就开始生产产品
        if (goods < 5) {
            this.notifyAll();
            goods++;
            System.out.println(Thread.currentThread().getName() + " 生产了第 " + goods + " 号产品");
        }
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 消费产品
    public synchronized void consumer() {
        if (goods > 0) {
            this.notifyAll();
            System.out.println(Thread.currentThread().getName() + " 消费产品" + goods--);
        }
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void lockProduct() {

        try {
            lock.lock();
            while (goods < 1) {
                condition.signal();
                goods++;
                System.out.println(Thread.currentThread().getName() + " 生产产品编号 " + goods);
            }
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void lockConsumer() {
        try {
            lock.lock();
            while (goods > 0) {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + " 消费 " + goods--);
            }
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    // wait() 使用while循环
    public void increment() {
        try {
            lock.lock();
            while (goods > 0) {
                condition.await();
            }
            goods++;
            System.out.println(Thread.currentThread().getName() + " 生产了产品号 " + goods);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 消费产品
    public void decrement() {
        try {
            lock.lock();
            while (goods < 1) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + " 消费编号 " + goods--);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}

