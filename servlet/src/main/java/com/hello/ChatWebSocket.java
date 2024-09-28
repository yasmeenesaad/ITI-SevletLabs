package com.hello;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/chat")
@ApplicationScoped
public class ChatWebSocket {

    private static final Set<Session> sessions = new HashSet<>();
    private static final Set<String> users = new HashSet<>();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // Extract username from message (assuming "username: message" format)
        String username = message.split(":")[0].trim();
        users.add(username);

        // Broadcast the updated user list
        broadcastUserList();

        // Broadcast the chat message to all sessions
        broadcastMessage(message);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        // Remove user (this can be optimized to track session-to-username mapping)
        users.removeIf(u -> u.equals(session.getUserProperties().get("username")));
        broadcastUserList();
    }

    private void broadcastMessage(String message) {
        for (Session ses : sessions) {
            try {
                if (ses.isOpen()) {
                    ses.getBasicRemote().sendText(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void broadcastUserList() {
        String userList = "USERLIST:" + String.join(",", users);
        for (Session ses : sessions) {
            try {
                if (ses.isOpen()) {
                    ses.getBasicRemote().sendText(userList);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
