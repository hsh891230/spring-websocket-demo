package com.example.springwebsocketdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ChatController {

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @MessageMapping("/join")
  @SendTo("/topic/chat")
  public String join(String message) {
    log.info("message - {}", message);
    return message;
  }

  @MessageMapping("/message")
  public void message(String message) {
    log.info("message - {}", message);
    messagingTemplate.convertAndSend("/topic/chat", message);
  }
  
}