package com.byteknowledge.bilt.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.byteknowledge.bilt.dao.TeamDao;
import com.byteknowledge.bilt.model.Team;

@Repository("teamDao")
public class TeamDaoRedis extends AbstractDaoRedis<Team> implements TeamDao {

    private static final String OBJECT_KEY = "Team";

    @Bean(name="teamRedisTemplate")
    public RedisTemplate<String,Team> redisTemplate() {
        return initRedisTemplate();
    }

    @Autowired
    @Qualifier("teamRedisTemplate")
    private RedisTemplate<String,Team> redisTemplate;

    @Override
    public String getObjectKey() {
        return OBJECT_KEY;
    }

    @Override
    public RedisTemplate<String,Team> getRedisTemplate() {
    	return redisTemplate;
    }

}
