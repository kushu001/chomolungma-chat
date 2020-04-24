package com.chomolungma.client;

import java.io.*;
import java.net.Socket;

public class ClientSocket {

    private Socket socket;
    private BufferedWriter osw;
    private BufferedReader br;
    // 建立连接
    public void connect(){
        try {
            socket = new Socket("127.0.0.1",9988);
            osw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (socket == null){
                System.out.println("建立连接失败....");
            }
        }

    }


    // 发送消息
    public String sendMessage(String str){
        if (socket == null){
            System.out.println("未建立连接");
        }

        String message = null;
        try {

            osw.write(str);
            osw.flush();
            while(true) {
                message = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return message;
    }

    //接收消息
    public String receiveMessage(){
        if (socket == null){
            System.out.println("未建立连接");
            return "";
        }



        String message = null;
        try {


            message = br.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return message;

    }


}
