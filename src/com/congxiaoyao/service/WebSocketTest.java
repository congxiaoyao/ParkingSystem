package com.congxiaoyao.service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by congxiaoyao on 2016/5/25.
 */

@ServerEndpoint("/websocket/test")
public class WebSocketTest {

    private static Map<String, Session> sessionMap =
            Collections.synchronizedMap(new HashMap<String, Session>());
    /**
     * 向客户端群发信息
     * @param data
     */
    public synchronized static void sendAll(String data){
        sessionMap.forEach((string,session)->{
            try {
                session.getBasicRemote().sendText(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @OnMessage
    public void onMessage(Session session, String msg){
        System.out.println("onMessage:" + msg);
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws IOException {
        sessionMap.put(session.getId(), session);
        session.getBasicRemote().sendText("welcome!");

        System.out.println("_________sessionId___________");
        sessionMap.forEach((str,ses)->{
            System.out.println(str);
        });

    }

    @OnError
    public void onError(Session session, Throwable throwable){
        throwable.printStackTrace();
    }

    @OnClose
    public void onClose(Session session, CloseReason reason){
        sessionMap.remove(session.getId());
    }

}
