package com.byteknowledge.bilt.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.byteknowledge.bilt.dao.GameWeekDao;
import com.byteknowledge.bilt.model.GameWeek;

@Repository("gameWeekDao")
public class GameWeekDaoRedis extends AbstractDaoRedis<GameWeek> implements GameWeekDao {

	private static final String OBJECT_KEY = "GameWeek";
	
    @Bean(name="gameWeekRedisTemplate")
    public RedisTemplate<String,GameWeek> redisTemplate() {
        return initRedisTemplate();
    }    
    
    @Autowired
    @Qualifier("gameWeekRedisTemplate")
    private RedisTemplate<String,GameWeek> redisTemplate;
    
    @Override
    public String getObjectKey() {
        return OBJECT_KEY;
    }
    
    @Override
    public RedisTemplate<String,GameWeek> getRedisTemplate() {
    	return redisTemplate;
    }
}
