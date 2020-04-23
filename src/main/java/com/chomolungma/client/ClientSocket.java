package com.chomolungma.client;

import java.io.*;
import java.net.Socket;

public class ClientSocket {

    private Socket socket;

    // 建立连接
    public void connect(){
        try {
            socket = new Socket("127.0.0.1",9988);
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
            return;
        }

        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(socket.getOutputStream());
            osw.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //接收消息
    public String receiveMessages(){
        if (socket == null){
            System.out.println("未建立连接");
            return "";
        }

        BufferedReader br = null;

        String message = null;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            message = br.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return message;

    }


}
