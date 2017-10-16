package jp.ac.hosei.servlet;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/wsdemo")
public class WebSocketDemo {

    private static Set<Session> ses = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen : " + session);
        ses.add(session);
    }

    @OnMessage
    public void onMessage(String text) {
    	System.out.println(text);
        sendMessage(text);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose : " + session);
        ses.remove(session);
    }

    public static void sendMessage(String msg) {
        for (Session ses : ses) {
            ses.getAsyncRemote().sendText(msg);
        }
    }
}
