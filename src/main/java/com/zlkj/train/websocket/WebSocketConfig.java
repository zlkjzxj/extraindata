package com.zlkj.train.websocket;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {


    @Bean
    public WebSocketHandler socketMessageHandler() {
        return new InitMessageHandler();
    }

    @Bean
    public WebSocketHandler exportProgressHandler() {
        return new ExportProgressHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketMessageHandler(), "/initMessage");
        registry.addHandler(exportProgressHandler(), "/exportProgress");
    }
}