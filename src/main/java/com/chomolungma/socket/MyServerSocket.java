package com.chomolungma.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSocket {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(62224);
            while (true){
                Socket socket = serverSocket.accept();
                //JOptionPane.showMessageDialog(null, "有客户端连接到了本机62224端口哦");
                //与客户端通信
                ChatSocket cs=new ChatSocket(socket);
                cs.start();
                //添加socket到队列
                ChatManager.GetChatManager().AddChatPeople(cs);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
