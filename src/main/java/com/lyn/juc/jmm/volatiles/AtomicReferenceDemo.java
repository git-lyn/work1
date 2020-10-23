package com.lyn.juc.jmm.volatiles;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用包装类：
 *  当自定义的类需要使用对应的原子操作时，就可以使用原子引用来进行处理
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(new User(20,"zs"));
        atomicReference.set(new User(55,"ls"));

    }
}

class User{
    private int id;
    private String username;

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }
}
