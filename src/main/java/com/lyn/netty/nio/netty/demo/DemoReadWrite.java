package com.lyn.netty.nio.netty.demo;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2020-07-20 17:23
 **/
public class DemoReadWrite {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("");
        FileOutputStream outputStream = new FileOutputStream("");
        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(100);
        while (true) {
            buffer.clear();
            int read = inputChannel.read(buffer);
            if (read == -1) {
                break;
            }
            buffer.flip();
            outputChannel.write(buffer);
        }
        outputStream.close();
        inputStream.close();

    }
}
