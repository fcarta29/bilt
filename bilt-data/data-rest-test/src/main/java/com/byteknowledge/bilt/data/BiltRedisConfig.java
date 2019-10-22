package com.byteknowledge.bilt.data;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.byteknowledge.bilt.data.receiver.DataReceiver;

@Configuration
@ComponentScan("com.byteknowledge.bilt")
@PropertySource("classpath:/redis.properties")
public class BiltRedisConfig {

    private @Value("${redis.host-name}") String redisHostName;
    private @Value("${redis.port}") int redisPort;

    @Autowired
    protected JedisConnectionFactory jedisConnectionFactory;
    
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
    
    @Bean(name = "redisIndexingTemplate")
    protected RedisTemplate<String, String> redisIndexingTemplate() {
        final RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }    
    
    @Bean
    public MessageListenerAdapter listenerAdapter(DataReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    DataReceiver receiver(CountDownLatch latch) {
        return new DataReceiver(latch);
    }

    @Bean
    CountDownLatch latch() {
        return new CountDownLatch(1);
    }

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("data"));

        return container;
    }

}
