package com.chomolungma.socket;

import java.io.*;
import java.net.Socket;

public class ChatSocket extends Thread {
    Socket socket;
    public ChatSocket(Socket socket) {
        this.socket=socket;
    }

    /*
     *向客户端输出信息
     */
    public void Out(String str) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
            bw.write(str);
            bw.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
     *
     * 向客户端发送消息
     */
    @Override
    public void run() {
        Out("success1231231231231231312313");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));

            String line=null;
            while((line=br.readLine())!=null)
            {
                System.out.println(line);
                ChatManager.GetChatManager().Send(this, line);
            }
            br.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
