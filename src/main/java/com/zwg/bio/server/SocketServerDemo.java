package com.zwg.bio.server;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zwg
 * @date 2020/2/5 17:03
 * @description :
 */
public class SocketServerDemo {

    public static void main(String[] args) throws IOException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6778);
        while (!Thread.currentThread().isInterrupted()){
           final Socket accept = serverSocket.accept();
            System.out.println("客户端连接成功!");

            executorService.execute(() -> {

                handle(accept);
            });




        }


    }

    private static void handle(Socket accept) {
       try {
           System.out.println(Thread.currentThread().getName());
           InputStream inputStream = accept.getInputStream();

           byte[] bytes = new byte[1024];

           while (!Thread.currentThread().isInterrupted()){
               System.out.println("222"+Thread.currentThread().getName());
               int flag;
               while ((flag=inputStream.read(bytes))!=-1){

                   System.out.println(new String(bytes,0,flag));
               }
           }



       }catch (Exception e){
           e.printStackTrace();
       }finally {
           try {
               accept.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }
}
