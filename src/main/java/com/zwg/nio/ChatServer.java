package com.example.demo.nio;

import sun.jvm.hotspot.debugger.posix.elf.ELFException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zwg
 * @date 2020/2/16 19:36
 * @description :
 */
public class ChatServer {


    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        SocketAddress socketAddress = new InetSocketAddress(6666);
        serverSocketChannel.bind(socketAddress);
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("selector.keys()0"+selector.keys().size());

        while (true){

            if(selector.select(1000)<=0){
                //在1000秒内没有新的channel有事件发生的所有的selectKey
                System.out.println("服务器没有新的客户端连接");
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selector.keys()1---->"+selector.keys().size());
            System.out.println("selectionKeys1------>"+selectionKeys.size());
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                if (next.isAcceptable()){
                    //是已经准备好连接事件
                    //获取到对应的socketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("获取到客户端连接"+socketChannel);
                    socketChannel.configureBlocking(false);
                    //将socketChannel注册到select中去,并关联为OP_READ,同时给给socketChannel关联一个buffer
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                }
                else if(next.isReadable()){
                    //已经准备好读事件
                    SocketChannel channel = (SocketChannel) next.channel();
                    //con通道获取数据并写的如到buffer中
                    StringBuilder s= new StringBuilder();
                    ByteBuffer attachment = (ByteBuffer) next.attachment();
                    int read = channel.read(attachment);
                    s.append(new String(attachment.array(),0,read));
                    System.out.println("从客户端获取的数据"+s.toString());
                }
                //手动移除当前的SelectionKey
                iterator.remove();

            }


        }


    }
}
