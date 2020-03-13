package com.zwg.bio.server;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
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

           byte[] bytes = new byte[1];
           byte[] allByte=new  byte[2046];
           int i=0;
               int i1=0;
           while ((i1=inputStream.read(bytes))!=-1){
               int available = inputStream.available();
               System.out.println("av->"+available);
               if (inputStream.available()<=0){
                   //本次最后一次读
                   System.out.println("haahh");
                   System.out.println(new String(allByte,0,i));
               }else {
                   for (byte aByte : bytes) {
                       Arrays.fill(allByte,i,i+1,aByte);
                   }
                   i+=i1;
               }

               }

           System.out.println("jisu1");

//               }





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
