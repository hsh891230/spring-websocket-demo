package com.example.springwebsocketdemo;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * MyHandler
 */
public class MyHandler extends TextWebSocketHandler {

  Set<WebSocketSession> sessions = new HashSet<>();

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    System.out.println("handleText session: " + session.getId() + ", Message :" + message);
    sessions.forEach(s -> {
      try {
        s.sendMessage(message);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    System.out.println("afterConnectionEstablished session: " + session.getId());
    sessions.add(session);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    System.out.println("afterConnectionClosed session: " + session.getId());
    sessions.remove(session);
    super.afterConnectionClosed(session, status);
  }
  
}