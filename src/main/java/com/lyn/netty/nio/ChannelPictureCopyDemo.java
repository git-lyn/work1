package com.lyn.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/** 使用channel实现图片的copy
 * @program: projects
 * @author: lyn
 * * @create: 2019-12-22 17:09
 **/
public class ChannelPictureCopyDemo {
    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("E:\\a.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\b.jpg");
        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        outputStreamChannel.transferFrom(inputStreamChannel, 0, inputStreamChannel.size());
        fileOutputStream.close();
        fileInputStream.close();
    }
}
