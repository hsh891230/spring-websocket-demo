package com.example.springwebsocketdemo;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@Profile("default")
@Configuration
public class SimpleWebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    // registry.addEndpoint("/chat").setAllowedOrigins("*").withSockJS()  // withSockJS를 활성화 한 경우 endpoint에 /websocket을 붙여줘야함.
    registry.addEndpoint("/chat").setAllowedOrigins("*");
  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry
      .setApplicationDestinationPrefixes("/app")
      .enableSimpleBroker("/topic");
  }

  @Bean
  public ApplicationListener<BrokerAvailabilityEvent> brokerAvailabilityEvent() {
    return (BrokerAvailabilityEvent event) -> {
      System.out.println(event);
    };
  }

  @Bean
  public ApplicationListener<SessionConnectEvent> sessionConnectEvent() {
    return (SessionConnectEvent event) -> {
      System.out.println(event);
    };
  }

  @Bean
  public ApplicationListener<SessionConnectedEvent> sessionConnectedEvent() {
    return (SessionConnectedEvent event) -> {
      System.out.println(event);
    };
  }

  @Bean
  public ApplicationListener<SessionSubscribeEvent> sessionSubscribeEvent() {
    return (SessionSubscribeEvent event) -> {
      System.out.println(event);
    };
  }

  @Bean
  public ApplicationListener<SessionUnsubscribeEvent> sessionUnsubscribeEvent() {
    return (SessionUnsubscribeEvent event) -> {
      System.out.println(event);
    };
  }

  @Bean
  public ApplicationListener<SessionDisconnectEvent> sessionDisconnectEvent() {
    return (SessionDisconnectEvent event) -> {
      System.out.println(event);
    };
  }

}