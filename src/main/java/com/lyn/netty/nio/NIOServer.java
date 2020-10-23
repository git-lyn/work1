package com.lyn.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2019-12-22 19:36
 **/
public class NIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 创建Selector对象
        Selector selector = Selector.open();
        // 绑定一个端口6666，在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        // 把serverSocketChannel 注册到 selector 事件为 OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 循环等待客户端连接
        while (true) {
            // 这里我们等待1秒，如果没有事件发生，就返回
            if (selector.select(1000) == 0) {
                // 没有事件发生
                System.out.println("服务器等待了1秒，没有连接");
                continue;
            }
            // 如果返回的>0,就获取到相关的selectionKey集合
            // 1. 如果返回的>0, 表示已经获取到关注的事件
            // 2. selector.selectKeys() 返回关注事件的集合
            // 通过selectioinKeys 返回获取socketChannel
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            // 遍历Set<SelectionKey>, 使用迭代器遍历
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                // 根据key对应的通道发生的事件做相应处理
                if (key.isAcceptable()) { // 如果是OP_ACCEPT, 有新的客户端连接
                    // 盖客户端生成一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功 生成一个socketChannel " + socketChannel.hashCode());
                    // 将socketChannel设置为非阻塞
                    // ******************
                    // Exception in thread "main" java.nio.channels.IllegalBlockingModeException
                    socketChannel.configureBlocking(false);
                    // 将socketChannel注册到selector，关注事件为OP_READ,同时给socketChannel关联一个buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (key.isReadable()) {// 发生OP_READ
                    // 通过key 反向获取到对应channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 获取到盖channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("form 客户端 " + new String(buffer.array()));
                }
                // 手动从集合中移动当前的selectionKey,防止重复操作
                keyIterator.remove();
            }
        }
    }
}
