package com.byteknowledge.bilt.services.movement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@ComponentScan("com.byteknowledge.bilt")
@PropertySource({"classpath:/websocket.properties", "classpath:/redis.properties"})
@EnableWebSocketMessageBroker
public class BiltGameConfig extends AbstractWebSocketMessageBrokerConfigurer {

    private @Value("${redis.host-name}") String redisHostName;
    private @Value("${redis.port}") int redisPort;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(redisHostName);
        factory.setPort(redisPort);
        factory.setUsePool(true);
        return factory;
    }

    @Override
    public void configureMessageBroker(final MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // outgoing pattern clients subscribe to (UI - web, app)
        config.setApplicationDestinationPrefixes("/app"); // incoming from clients ( UI - web, app)
    }

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/bilt") // endpoint that stomp client connects to
            .setAllowedOrigins("*") // TODO[fcarta] for now allow all - needed since seperate apps cause CORS errors
            .withSockJS();
    }
}
