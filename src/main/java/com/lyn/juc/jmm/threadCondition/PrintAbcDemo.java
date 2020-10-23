package com.lyn.juc.jmm.threadCondition;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 打印ABC 三个
 * AA 5遍  BB 10遍  CC  20遍  连续打印10次
 *
 * @program: projects
 * @author: lyn
 * * @create: 2019-12-13 16:39
 **/
public class PrintAbcDemo {
    public static void main(String[] args) {
//        lock();
//        semephore();
//        semephoreAll();
//        sync();
        syncAll();
    }

    public static void semephore() {
        SemaphorPrint lockPrint = new SemaphorPrint();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                lockPrint.A(i);
            }
        }, "11111").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                lockPrint.B(i);
            }
        }, "22222").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                lockPrint.C(i);
            }
        }, "33333").start();
    }

    public static void semephoreAll() {
        SemaphorePrintAll lockPrint = new SemaphorePrintAll();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                lockPrint.printAll(i);
            }
        }, "11111").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                lockPrint.printAll(i);
            }
        }, "22222").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                lockPrint.printAll(i);
            }
        }, "33333").start();
    }


    public static void lock() {
        LockPrint lockPrint = new LockPrint();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                lockPrint.A(i);
            }
        }, "11111").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                lockPrint.B(i);
            }
        }, "22222").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                lockPrint.C(i);
            }
        }, "33333").start();
    }

    public static void syncAll() {
        SynchroPrint print = new SynchroPrint();
        // 进行多线程数据的通信
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                print.printAll(i);
            }
        }, "11111").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                print.printAll(i);
            }
        }, "22222").start();

        new Thread(() -> {

            for (int i = 1; i <= 10; i++) {
                print.printAll(i);
            }
        }, "33333").start();

    }

    public static void sync() {
        SynchroPrint print = new SynchroPrint();
        // 进行多线程数据的通信
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                print.printA(i);
            }
        }, "11111").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                print.printB(i);
            }
        }, "22222").start();

        new Thread(() -> {

            for (int i = 1; i <= 10; i++) {
                print.printC(i);
            }
        }, "33333").start();

    }

}

/**
 * 使用sync关键字进行线程通信
 */
class SynchroPrint {
    private int flag = 1;

    public void printAll(int temp) {
        synchronized (this) {
            while (flag != 1) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (flag == 1) {
                System.out.println("第" + temp + "轮" + Thread.currentThread().getName() + "  AAA");
                flag = 2;
                this.notifyAll();
//                return;
            }
        }

        synchronized (this) {
            while (flag != 2) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (flag == 2) {
                System.out.println("第" + temp + "轮" + Thread.currentThread().getName() + "  BBB");
                flag = 3;
                this.notifyAll();
            }
        }

        synchronized (this) {
            while (flag != 3) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (flag == 3) {
                System.out.println("第" + temp + "轮" + Thread.currentThread().getName() + "  CCC");
                flag = 1;
                this.notifyAll();
            }
        }


    }

    public void printA(int temp) {
        synchronized (this) {
            while (flag != 1) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("第" + temp + "轮" + Thread.currentThread().getName() + "  AAA");
            flag = 2;
            this.notifyAll();
        }
    }

    public void printB(int temp) {
        synchronized (this) {
            while (flag != 2) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("第" + temp + "轮" + Thread.currentThread().getName() + "  BBB");
            flag = 3;
            this.notifyAll();
        }
    }

    public void printC(int temp) {
        synchronized (this) {
            while (flag != 3) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("第" + temp + "轮" + Thread.currentThread().getName() + "  CCC");
            flag = 1;
            this.notifyAll();
        }
    }

}

class LockPrint {
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int flag = 1;

    public void A(int te) {
        try {
            lock.lock();
            while (flag != 1) {
                condition1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println("第 " + te + " 轮: " + Thread.currentThread().getName() + " **** " + i + ":  AA ");
            }
            flag = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void B(int te) {
        try {
            lock.lock();
            while (flag != 2) {
                condition2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println("第 " + te + " 轮: " + Thread.currentThread().getName() + " @@@@ " + i + ":  BB ");
            }
            flag = 3;
            // 唤醒第三个线程
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void C(int te) {
        try {
            lock.lock();
            while (flag != 3) {
                condition3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println("第 " + te + " 轮: " + Thread.currentThread().getName() + " #### " + i + ":  CC ");
            }
            flag = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

/**
 * 使用Semaphore信号量来实现相应的A、B、C循环打印
 */
class SemaphorPrint {
    private Semaphore semaphore = new Semaphore(1);
    private Semaphore semaphore2 = new Semaphore(0);
    private Semaphore semaphore3 = new Semaphore(0);
    private int flag = 1;

    public void A(int temp) {
//        if (flag == 1) {
        try {
            semaphore.acquire();
            System.out.println("第 " + temp + " 轮: " + Thread.currentThread().getName() + " #### " + ":  AA ");
            flag = 2;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore2.release();
        }
//        }
    }

    public void B(int temp) {
//        if (flag == 2) {
        try {
            semaphore2.acquire();
            System.out.println("第 " + temp + " 轮: " + Thread.currentThread().getName() + " #### " + ":  BB ");
            flag = 3;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore3.release();
        }
//        }
    }

    public void C(int temp) {
//        if (flag == 3) {
        try {
            semaphore3.acquire();
            System.out.println("第 " + temp + " 轮: " + Thread.currentThread().getName() + " #### " + ":  CC ");
            flag = 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
//        }
    }


}

class SemaphorePrintAll {
    private Semaphore semaphore1 = new Semaphore(1);
    private Semaphore semaphore2 = new Semaphore(0);
    private Semaphore semaphore3 = new Semaphore(0);
    private int flag = 1;

    public void printAll(int temp) {
        try {
            semaphore1.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (flag == 1) {
            flag = 2;
            System.out.println("第 " + temp + " 轮: " + Thread.currentThread().getName() + " #### " + ":  AAA ");
//            return;
        }
        try {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore2.release();
        }

        try {
            semaphore2.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (flag == 2) {
            System.out.println("第 " + temp + " 轮: " + Thread.currentThread().getName() + " #### " + ":  BBB ");
            flag = 3;
//            return;
        }
        try {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore3.release();
        }

        try {
            semaphore3.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (flag == 3) {
            System.out.println("第 " + temp + " 轮: " + Thread.currentThread().getName() + " #### " + ":  BBB ");
            flag = 1;
//            return;
        }
        try {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore1.release();
        }

    }


}

