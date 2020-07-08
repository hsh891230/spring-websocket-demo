package com.example.springwebsocketdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

// @Configuration
// @EnableWebSocket
@Profile("websocket")
public class NativeWebSocketConfig implements WebSocketConfigurer {

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(myHandler(), "/myHandler")
      .setAllowedOrigins("*")
      .addInterceptors(new HttpSessionHandshakeInterceptor());
  }

  @Bean
  public WebSocketHandler myHandler() {
    return new MyHandler();
  }

}