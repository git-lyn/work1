package com.lyn.juc.jmm.volatiles;

/**
 * 双端检锁机制：DCL
 * 禁止指令重排Volatile
 */
public class SingletonDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Singleton.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
class Singleton{
    private static volatile Singleton instance = null;
    private Singleton(){
        System.out.println(Thread.currentThread().getName() + "\t" + "调用单例的构造器");
    }
    // DCL (Double Check Lock 双端检锁机制)
    public static  Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
//    public static synchronized Singleton getInstance(){
//        if(instance == null){
//            instance = new Singleton();
//        }
//        return instance;
//    }
}

/**
 * 使用DCL(双端检锁机制）和Volatile禁止指令重排，实现高并发的单例模式
 */
class MySingleton{
    private static volatile MySingleton instance = null;
    private MySingleton(){
        System.out.println(Thread.currentThread().getName() + "\t生成实例对象");
    }
    // DCL
    public static MySingleton getInstance(){
        if(instance == null){
            synchronized (MySingleton.class){
                if(instance == null){
                    instance = new MySingleton();
                }
            }
        }
        return instance;
    }
}




class SingletonVolatile{
    private static volatile SingletonVolatile instance = null;


    /**
     * DCL: 双端检索机制
     * @return
     */
    public static SingletonVolatile getInstance(){
        if(instance == null){
            synchronized(SingletonVolatile.class){
                // 进行双向判断数据信息
                if (instance == null) {
                    instance = new SingletonVolatile();
                }
            }
        }
        return  instance;
    }

}
