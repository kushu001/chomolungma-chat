package com.chomolungma.chat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

@SuppressWarnings("serial")
public class ChatClient extends Frame {
    private TextArea ta = new TextArea();
    private TextField tf = new TextField();
    private DataOutputStream dos = null;
    private DataInputStream dis = null;
    private Socket socket = null;
    private boolean bConnected = false;
    private Thread thread=null;
    public static void main(String[] args) {
        new ChatClient().frameClient();
    }
    public void frameClient(){
        setSize(400, 400);
        setLocation(400,300);
        add(ta, BorderLayout.NORTH);
        add(tf,BorderLayout.SOUTH);
        pack();
        tf.addActionListener(new TfListener());
        //关闭窗口事件监听
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                disconnected();
                System.exit(0);
            }
        });
        this.connect();
        setVisible(true);
    }
    //链接服务器地址
    private void connect(){
        try {
            socket = new Socket("127.0.0.1", 8888);
            thread=new Thread(new ChatThread());
            thread.start();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //断开连接
    private void disconnected(){
        bConnected = false;
        try {
            dos.close();
            dis.close();
            socket.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
    //键盘回车事件
    private class TfListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String strMsg = tf.getText();
            tf.setText("");
            try {
                dos.writeUTF(strMsg);
                dos.flush();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }
    //开启线程接受服务器信息
    private class ChatThread implements Runnable{
        @Override
        public void run() {
            try {
                bConnected = true;
                dos = new DataOutputStream(socket.getOutputStream());
                dis = new DataInputStream(socket.getInputStream());
                while(bConnected){
                    String msg = dis.readUTF();
                    String taText = ta.getText();
                    ta.setText(taText+msg+"\n");
                }
            } catch (SocketException e) {
                System.out.println("退出");;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
