package com.lyn.netty.nio.netty.bio;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @program: projects
 * @author: lyn
 * * @create: 2020-07-21 21:22
 **/
public class BioClient {
    public static void main(String[] args) {
        ma();
//        try {
//            Socket socket = new Socket("127.0.0.1", 6789);
//            new Thread(() ->{
//                while (true) {
//                    byte[] b = new byte[1024];
//                    try {
//                        int read = socket.getInputStream().read(b);
//                        if (read > 0) {
//                            System.out.println(new String(b));
//                            break;
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//            while (true) {
//                Scanner scanner = new Scanner(System.in);
//                while (scanner.hasNextLine()) {
//                    String s = scanner.nextLine();
//                    socket.getOutputStream().write(s.getBytes());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static void ma(){
        try {
            Socket socket=new Socket("127.0.0.1",1333);
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        try {
                            byte[] b=new byte[1024];
                            int read = socket.getInputStream().read(b);
                            if(read>0){
                                System.out.println(new String(b));
                                break;
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }.start();

            while (true){
                Scanner scanner=new Scanner(System.in);
                while(scanner.hasNextLine()){
                    String s = scanner.nextLine();
                    socket.getOutputStream().write(s .getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
