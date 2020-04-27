package com.chomolungma.server;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientThread extends Thread{

    private Socket socket;


    public ClientThread(Socket socket){
        this.socket = socket;

    }

    /*
     *向客户端输出信息
     */
    public void Out(String str) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
            bw.write(str+"\n");
            bw.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        BufferedReader br = null;
        try {
            String line=null;
            while((line=br.readLine())!=null)
            {
                System.out.println(line);
                ChatManager.GetChatManager().Send(this, line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }





    }
}
