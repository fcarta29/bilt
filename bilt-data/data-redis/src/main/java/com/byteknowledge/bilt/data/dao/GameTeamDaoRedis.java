package com.byteknowledge.bilt.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.byteknowledge.bilt.dao.GameTeamDao;
import com.byteknowledge.bilt.model.GameTeam;

@Repository("gameTeamDao")
public class GameTeamDaoRedis extends AbstractDaoRedis<GameTeam> implements GameTeamDao {

	private static final String OBJECT_KEY = "GameTeam";
	
    @Bean(name="gameTeamRedisTemplate")
    public RedisTemplate<String,GameTeam> redisTemplate() {
        return initRedisTemplate();
    }    
    
    @Autowired
    @Qualifier("gameTeamRedisTemplate")
    private RedisTemplate<String,GameTeam> redisTemplate;
    
    @Override
    public String getObjectKey() {
        return OBJECT_KEY;
    }
    
    @Override
    public RedisTemplate<String,GameTeam> getRedisTemplate() {
    	return redisTemplate;
    }
}
