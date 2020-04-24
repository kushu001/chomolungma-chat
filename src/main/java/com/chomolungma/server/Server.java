package com.chomolungma.server;

import com.chomolungma.client.ClientSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void main(String[] args) {
        List<ClientSocketTask> serverSockets = new ArrayList<>();
        try {
            ServerSocket serverSocket = new ServerSocket(9988);

            while (true){
                Socket server = serverSocket.accept();
                ClientSocketTask clientSocket = new ClientSocketTask(server,serverSockets);
                serverSockets.add(clientSocket);
                clientSocket.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
