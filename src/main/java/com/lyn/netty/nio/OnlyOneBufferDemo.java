package com.lyn.netty.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/** 使用一个Buffer实现文件的读取和写入功能
 * @program: projects
 * @author: lyn
 * * @create: 2019-12-22 16:56
 **/
public class OnlyOneBufferDemo {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("E:\\hello.txt");
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        FileOutputStream fileOutputStream = new FileOutputStream("E:\\hello2.txt");
        FileChannel channel2 = fileOutputStream.getChannel();

        // 读取channel中的数据
        while (true) {
            // 将数据进行还原
            //byteBuffer.clear();
            int read = fileChannel.read(byteBuffer);
            System.out.println("read: " + read);
            if (read == -1) {
                break;
            }
            // 在写入数据前，不要忘记将position反转
            byteBuffer.flip();
            // 写入channel数据
            channel2.write(byteBuffer);
            System.out.println(byteBuffer.position() + "  3333: " + byteBuffer.limit());
        }

    }
}
