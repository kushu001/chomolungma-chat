package com.chomolungma.socket;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ChatManager {
    //因为一个聊天系统只有一个聊天管理，所以需进行单例化private
    /*
     *单例化
     */
    private ChatManager() {}
    private static final ChatManager cm=new ChatManager();
    public static ChatManager GetChatManager() {
        return cm;
    }

    //创建保存socket的队列
    /*List<ChatSocket> vector=new ArrayList<>();*/
    Map<String,ChatSocket> clientCache = new HashMap<>();

    //添加聊天人
    public void AddChatPeople(ChatSocket cs) {
        clientCache.put(cs.getName(),cs);
    }

    //群发消息
    public void Send(ChatSocket cs,String str) {
         Iterator<String> keys = clientCache.keySet().iterator();
         while(keys.hasNext()){
             String key = keys.next();
             if (!cs.getName().equals(key)){
                 clientCache.get(key).Out(str);
             }
         }


    }
}
