package com.chomolungma.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private boolean started = false;
    private List<ChatThread> chatThreads = new ArrayList<ChatThread>();
    public static void main(String[] args) {
        new ChatServer().startServer();
    }
    private void startServer(){
        try {
            //开启服务端Socket
            ServerSocket seso = new ServerSocket(8888);
            started = true;
            while(started){
                //接受客户端连接请求
                Socket sos = seso.accept();
                System.out.println("一个客户端已连接");
                //开启线程处理客户端通信
                ChatThread ct = new ChatThread(sos);
                chatThreads.add(ct);
                new Thread(ct).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private class ChatThread implements Runnable{
        private Socket socket;
        private DataInputStream din=null;
        private DataOutputStream don=null;
        private boolean bConnected = false;
        public ChatThread(Socket socket) {
            super();
            this.socket = socket;
        }
        //发送信息的函数
        private void send(String strMsgIn){
            try{
                don.writeUTF(strMsgIn);
                don.flush();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            try{
                din = new DataInputStream(socket.getInputStream());
                don = new DataOutputStream(socket.getOutputStream());
                //读取数据
                bConnected = true;
                while(bConnected){
                    String strMsgIn = din.readUTF();
                    System.out.println(strMsgIn);
                    //接收到数据后发送给每个客户端
                    for(int i =0;i<chatThreads.size();i++){
                        chatThreads.get(i).send(strMsgIn);
                    }
                }
            }catch (IOException e) {
                try {
                    //如果客户端出错或关闭，直接关闭连接，并移除List中的当前线程
                    socket.close();
                    chatThreads.remove(this);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } finally{
                try {
                    din.close();
                    don.close();
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }
}
