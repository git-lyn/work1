package com.lyn.netty.nio.netty.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2020-07-21 20:23
 **/
public class ChatServer {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private long timeout = 2000;

    public ChatServer(){
       // 服务端channel
        try {
            serverSocketChannel = ServerSocketChannel.open();
            // 选择器对象
            selector = Selector.open();
            // 绑定端口
            serverSocketChannel.bind(new InetSocketAddress(9090));
            // 设置非阻塞式
            serverSocketChannel.configureBlocking(false);

            // 把servlerSocketChannel注册给selector
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // 监听连接

            System.out.println("服务端准备就绪");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        int count = 0;

    }

    public void connect(){
        while (true) {
            try {
                selector.select(timeout);

                // 得到SelectorKey对象，判断是什么事件, 连接事件
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    // 是连接事件
                    if (selectionKey.isAcceptable()) {
                        // 获取网络通道
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        // 设置非阻塞式
                        socketChannel.configureBlocking(false);
                        // 连接上了 注册读取事件
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println(socketChannel.getRemoteAddress().toString() + " 上线了");
                    }
                    // 读取客户端数据事件
                    if (selectionKey.isReadable()) {
                        // 读取客户端发来的数据
                        readClientData(selectionKey);
                        System.out.println("isReadable");
                    }
                    // 手动从当前集合将本次运行完的对象删除
                    iterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void rebuildSelector() throws IOException{
        Selector newSelector = Selector.open();
        Selector oldSelect = selector;
        for (SelectionKey selectionKey : oldSelect.keys()) {
            int i = selectionKey.interestOps();
            selectionKey.cancel();;
            selectionKey.channel().register(newSelector, i);
        }
        selector = newSelector;
        oldSelect.close();
    }

    // 读取客户端发来的数据
    private void readClientData(SelectionKey selectionKey) {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            int read = socketChannel.read(buffer);
            buffer.flip();
            if (read > 0) {
                byte[] bytes = new byte[read];
                buffer.get(bytes, 0, read);
                // 读取了数据 广播
                String s = new String(bytes, "utf-8");
                writeClientData(socketChannel,s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 广播 将读取的数据进行群发
    private void writeClientData(SocketChannel socketChannel, String s) {
        Set<SelectionKey> keys = selector.keys();
        for (SelectionKey key : keys) {
            if (key.isValid()) {
                SelectableChannel channel = key.channel();
                if (channel instanceof SocketChannel) {
                    SocketChannel socketChannel1 = (SocketChannel) channel;
                    if (channel != socketChannel) {
                        ByteBuffer wrap = ByteBuffer.wrap(s.getBytes());
                        try {
                            socketChannel1.write(wrap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new ChatServer().connect();
    }

}
