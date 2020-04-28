package com.chomolungma.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9988);


            while (true){
                Socket client = serverSocket.accept();
                ClientThread th = new ClientThread(client);
                ChatManager.GetChatManager().AddChatPeople(th);
                th.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
