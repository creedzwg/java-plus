package com.example.demo.nio;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.stream.IntStream;

/**
 * @author zwg
 * @date 2020/2/16 19:36
 * @description :
 */
public class ChatClient {


    public static void main(String[] args) throws IOException {




        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(6666));
        socketChannel.configureBlocking(false);
        String s="hello ,我来自客户端一号";
        socketChannel.write(ByteBuffer.wrap(s.getBytes()));

//        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//        int write = socketChannel.write(byteBuffer);
//        System.out.println(write);
        System.in.read();


    }
}
