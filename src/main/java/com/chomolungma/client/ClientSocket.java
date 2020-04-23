package com.chomolungma.client;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientSocket {

    private Socket socket;
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


    public void sendMessage(String str){
        if (socket == null){
            System.out.println("未建立连接");
            return;
        }

        OutputStreamWriter osw;
        try {
            osw = new OutputStreamWriter(socket.getOutputStream());
            osw.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String receiveMessages(){
        if (socket == null){
            System.out.println("未建立连接");
            return "";
        }

        InputStreamReader isr;


        try {
            isr = new InputStreamReader(socket.getInputStream());
            int ch;
            while((ch = isr.read() )!= -1){
                System.out.println((char) ch);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";

    }


}
