package com.chomolungma.client;


import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class OutputSocketThread extends Thread{

    private Socket socket;

    private TextArea messagesArea;



    public OutputSocketThread(Socket socket,TextArea messagesArea){
        this.socket = socket;
        this.messagesArea = messagesArea;

    }

    @Override
    public void run() {
        System.out.println(this.getName());
        try {
            BufferedReader bw = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = "";
            String message = "";
            while((line = bw.readLine()) != null){
                message += line+"\n";
                if (line.endsWith("EOF")){
                    message = message.substring(0,message.length()-4);
                    messagesArea.appendText(message+"\n");
                    message ="";
                }


            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
