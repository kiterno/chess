package com.chess.backend.configs

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer


@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig: WebSocketMessageBrokerConfigurer {


//    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
//        registry.addEndpoint("/gameplay").withSockJS()
//    }
//
//    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
//        registry.setApplicationDestinationPrefixes("/app").enableSimpleBroker("/topic")
//    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/sow").withSockJS()
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.setApplicationDestinationPrefixes("/app").enableSimpleBroker("/topic")
    }
}