package com.byteknowledge.bilt.data.dao;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.byteknowledge.bilt.dao.GamePickDao;
import com.byteknowledge.bilt.model.GamePick;

@Repository("gamePickDao")
public class GamePickDaoRedis extends AbstractDaoRedis<GamePick> implements GamePickDao {

	private static final String OBJECT_KEY = "GamePick";
	
    @Bean(name="gamePickRedisTemplate")
    public RedisTemplate<String,GamePick> redisTemplate() {
        return initRedisTemplate();
    }    
    
    @Autowired
    @Qualifier("gamePickRedisTemplate")
    private RedisTemplate<String,GamePick> redisTemplate;
    
    @Override
    public String getObjectKey() {
        return OBJECT_KEY;
    }
    
    @Override
    public RedisTemplate<String,GamePick> getRedisTemplate() {
    	return redisTemplate;
    }

	@Override
	public List<GamePick> getByGames(Collection<UUID> gameIds) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public GamePick getByGameAndUser(UUID gameId, UUID userId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
