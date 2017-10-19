package com.byteknowledge.bilt.data.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.byteknowledge.bilt.dao.GameDao;
import com.byteknowledge.bilt.model.Game;

@Repository("gameDao")
public class GameDaoRedis extends AbstractDaoRedis<Game> implements GameDao {

	private static final String OBJECT_KEY = "Game";
	
    @Bean(name="gameRedisTemplate")
    public RedisTemplate<String,Game> redisTemplate() {
        return initRedisTemplate();
    }    
    
    @Autowired
    @Qualifier("gameRedisTemplate")
    private RedisTemplate<String,Game> redisTemplate;
    
    @Override
    public String getObjectKey() {
        return OBJECT_KEY;
    }
    
    @Override
    public RedisTemplate<String,Game> getRedisTemplate() {
    	return redisTemplate;
    }

	@Override
	public List<Game> getByWeek(final UUID weekId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
