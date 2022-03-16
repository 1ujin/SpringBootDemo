package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 添加一个/gs-guide-websocket端点，客户端就可以通过这个端点来进行连接；
     * withSockJS作用是添加SockJS支持,
     * setAllowedOrigins(String... var1) 指定可以跨域访问的地址
     * setHeartbeatTime 设置心跳间隔时间，确保连接不断开，默认为25000ms
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").withSockJS().setHeartbeatTime(1000);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*
         * config.enableSimpleBroker("/topic","/user");
         * 这句表示在topic和user这两个域上可以向客户端发消息
         * config.setUserDestinationPrefix("/user/");
         * 这句表示给指定用户发送（一对一）的主题前缀是"/user/"
         * config.setApplicationDestinationPrefixes("/app");
         * 这句表示客户端向服务端发送时的主题上面需要加"/app"作为前缀
         * registry.addEndpoint("/webServer").withSockJS();
         * 这个和客户端创建连接时的url有关，后面在客户端的代码中可以看到
         */
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

}
