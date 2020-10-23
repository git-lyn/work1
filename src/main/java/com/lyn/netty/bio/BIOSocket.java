package com.lyn.netty.bio;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-12-22 13:13
 **/
public class BIOSocket {
    public static void main(String[] args) throws IOException {

        // 采用线程池机制
        // 1. 创建一个线程池
        // 2. 如果有客户端连接，就创建一个线程与之相连

        ExecutorService executorService = Executors.newCachedThreadPool();
        // 创建ServerSocket, 并监控6666端口号
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动了");

        while (true) {
            System.out.println("连接一个客户端");
            // 没有客户端连接时，进行阻塞
            System.out.println("等待连接............");
            // 监听,等待客户端的连接
            final Socket socket = serverSocket.accept();
            // 创建一个线程，与之通信(单独写一个方法)
            executorService.execute(() -> {
                // 可以和客户端通信
                getMessage(socket);
            });
        }
    }

    // 编写一个方法，与客户端通讯
    public static void getMessage(Socket socket) {
        try {
            System.out.println("@@@@@@: " + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            // 通过socket获取输入流
            InputStream inputStream = socket.getInputStream();
            // 循环的读取客户端发送的数据
            while (true) {
                System.out.println("#####: " + Thread.currentThread().getName());
                // 客户端没有发送数据，也会阻塞
                System.out.println("read..........");
                int read = inputStream.read(bytes);
                if (read != -1) {
                    // 输出客户端发送的信息
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
