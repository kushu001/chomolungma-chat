package com.chomolungma.server;

import com.chomolungma.client.ClientSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9988);


            while (true){
                Socket client = serverSocket.accept();
                ClientThread th = new ClientThread(client);
                th.start();
                ChatManager.GetChatManager().AddChatPeople(th);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
