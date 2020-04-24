package com.chomolungma.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ClientSocketTask extends Thread {

    private Socket socket;
    private List<ClientSocketTask> clients;
    private BufferedWriter osw;
    private BufferedReader br;
    public ClientSocketTask(Socket socket, List<ClientSocketTask> clients){
        this.socket = socket;
        this.clients = clients;
    }

    @Override
    public void run() {


        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            osw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            while(true){
                String message = br.readLine();

                System.out.println(message);
                for (ClientSocketTask client: clients) {
                    client.sendMsg(message+"\n");
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void sendMsg(String msg){
        try {
            osw.write(msg);
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
