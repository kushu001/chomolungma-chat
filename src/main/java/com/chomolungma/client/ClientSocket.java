package com.chomolungma.client;


import javafx.scene.control.TextArea;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientSocket {

    private Socket socket;
    private BufferedWriter osw;
    private TextArea messagesArea;
    private String name;

    public ClientSocket(TextArea messagesArea,String name){
        this.messagesArea= messagesArea;
        this.name = name;
    }

    // 建立连接
    public void connect(){
        try {
            socket = new Socket("127.0.0.1",9988);
            new OutputSocketThread(socket,messagesArea).start();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (socket == null){
                System.out.println("建立连接失败....");
            }
        }

    }

    // 发送消息
    public void sendMessage(String str){
        if (socket == null){
            System.out.println("未建立连接");
        }

        try {
            osw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            osw.write(name+":\n"+str+"EOF\n");
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public Socket getSocket(){
        return this.socket;
    }



}
