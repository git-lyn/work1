package com.lyn.netty.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**  将字符串的数据写入到文件中: 通过channel的方式
 * @program: projects
 * @author: lyn
 * * @create: 2019-12-22 16:06
 **/
public class FileChannelDemo {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\hello.txt");
        // 通过io流获取对应的channel
        FileChannel channel = fileOutputStream.getChannel();
        // 创建ByteBuffer, 写入数据到channel
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        String str = "hello World!";
        byteBuffer.put(str.getBytes());
        // 进行position的反转, 将position设置为0，limit设置为数据的长度
        byteBuffer.flip();
        // channel写入数据: 将buffer中的数据写入到channel
        channel.write(byteBuffer);
        fileOutputStream.close();
    }
}
