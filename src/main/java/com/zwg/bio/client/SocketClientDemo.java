package com.zwg.bio.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zwg
 * @date 2020/2/5 17:12
 * @description :
 */
public class SocketClientDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 6778);
        OutputStream outputStream = socket.getOutputStream();
        byte[] bytes = "wert34343xzxzq切尔奇二二翁2亲亲我群无12122121".getBytes("UTF-8");
        System.out.println(bytes.length);
        outputStream.write(bytes);
        System.in.read();
        Thread.sleep(5000);
        outputStream.close();


    }
}
