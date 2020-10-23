package com.lyn.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-12-22 20:08
 **/
public class NIOClient {
    public static void main(String[] args) throws Exception {
        // 得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        // 设置为非阻塞
        socketChannel.configureBlocking(false);
        // 提供服务器端的ip和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        // 连接服务器
        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("因为连接需要事件，客户端不会阻塞，可以做其他工作。。。");
            }
        }
        // 如果连接成功，就发送数据
        String str = "hello 你好";
        // wraps
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        // 发送数据，将buffer数据写入channel
        socketChannel.write(byteBuffer);
        // 代码停止在这里
        System.in.read();
    }
}
