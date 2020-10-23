package com.lyn.netty.nio;

import java.nio.IntBuffer;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-12-22 14:32
 **/
public class BufferDemo {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for(int i = 0;i < intBuffer.capacity();i++) {
            intBuffer.put(i * 2);
        }
        // 进行获取数据的操作
        // buffer既能读取数据也能写入数据, 但是需要flip() 方法进行切换
        intBuffer.flip();
        // 判断是否还有数据存在
        while (intBuffer.hasRemaining()) {
            // 获取buffer中的所有数据
            System.out.println(intBuffer.get());
        }
    }
}
