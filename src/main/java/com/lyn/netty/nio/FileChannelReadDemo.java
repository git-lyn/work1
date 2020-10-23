package com.lyn.netty.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/** 使用channel读取文件中的信息
 * @program: projects
 * @author: lyn
 * * @create: 2019-12-22 16:30
 **/
public class FileChannelReadDemo {
    public static void main(String[] args) throws Exception {
        File file = new File("E:\\hello.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        // 获取对应的channel
        FileChannel fileChannel = fileInputStream.getChannel();
        // 创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        // channel读取数据到buffer
        fileChannel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }
}
