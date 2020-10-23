package com.lyn.netty.nio.netty.luban.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2020-07-22 11:14
 **/
public class TestByteBuf {
    public static void main(String[] args) {
        // 创建一个非池化的byteBuf, 大小为10个字节
        ByteBuf buf = Unpooled.buffer(10);

        // 2. 写入一段内容
        byte[] bytes = {1, 2, 3, 4, 5};
        buf.writeBytes(bytes);

        //3. 读取一段内容
        byte b1 = buf.readByte();
        byte b2 = buf.readByte();

        // 4.将读取的内容丢弃
        buf.discardReadBytes();

        // 5.清空读写指针
        buf.clear();


    }
}
