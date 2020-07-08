package com.example.springwebsocketdemo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.Setter;

@Profile("production")
@Configuration
@ConfigurationProperties("stomp")
@Setter
public class ExternalWebSocketConfig implements WebSocketMessageBrokerConfigurer {

  private String relayHost;

  private int relayPort;

  private String systemLogin;

  private String systemPasscode;

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/chat").setAllowedOrigins("*");
  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry
      .setApplicationDestinationPrefixes("/app")
      .enableStompBrokerRelay("/topic", "/queue")
      .setRelayHost(relayHost)
      .setRelayPort(relayPort)
      .setSystemLogin(systemLogin)
      .setSystemPasscode(systemPasscode)
      ;
  }

}