package com.byteknowledge.bilt.services.movement.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.byteknowledge.bilt.dto.GameUpdateDto;

@Controller
public class GameController {

    private final static Logger LOG = Logger.getLogger(GameController.class);

    @Autowired
    protected JedisConnectionFactory jedisConnectionFactory;

    protected RedisTemplate<String,GameUpdateDto> initRedisTemplate() {
        final RedisTemplate<String,GameUpdateDto> redisTemplate = new RedisTemplate<String,GameUpdateDto>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<GameUpdateDto>(GameUpdateDto.class));
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<GameUpdateDto>(GameUpdateDto.class));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean(name="redisTemplate")
    public RedisTemplate<String,GameUpdateDto> redisTemplate() {
        return initRedisTemplate();
    }

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String,GameUpdateDto> redisTemplate;

    @MessageMapping("/game/update")
    @SendTo(value={"/topic/gameUpdate"})
    public GameUpdateDto updateGame(final GameUpdateDto gameUpdate) {
        if (gameUpdate != null && hasPermissionToMove(gameUpdate)) {
            LOG.debug(gameUpdate);
            //redisTemplate.convertAndSend("data", gameUpdate);
            return gameUpdate;
        }

        throw new IllegalArgumentException("Invalid or empty gameUpdate\n " + gameUpdate);
    }

    public boolean hasPermissionToMove(final GameUpdateDto gameUpdate) {
        return Boolean.TRUE;
    }
}
