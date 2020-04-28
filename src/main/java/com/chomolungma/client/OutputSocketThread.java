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
        try {
            BufferedReader bw = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = null;

            while((line = bw.readLine()) != null){
                messagesArea.setText(messagesArea.getText()+"\n"+line);
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
