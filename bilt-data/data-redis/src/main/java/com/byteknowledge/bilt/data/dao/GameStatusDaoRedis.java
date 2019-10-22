package com.byteknowledge.bilt.data.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

import com.byteknowledge.bilt.dao.GameStatusDao;
import com.byteknowledge.bilt.model.GameStatus;

@Configuration
@Repository("gameStatusDao")
public class GameStatusDaoRedis implements GameStatusDao {

	private static final String OBJECT_KEY = "GameStatus";

	@Autowired
	protected JedisConnectionFactory jedisConnectionFactory;

    @Bean(name="gameStatusRedisTemplate")
    public RedisTemplate<String,GameStatus> redisTemplate() {
		final RedisTemplate<String, GameStatus> redisTemplate = new RedisTemplate<String, GameStatus>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
		// NOTE[fcarta] - TX seem to perform really poorly disabling for now
		// redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<GameStatus>(GameStatus.class));
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
    }

    @Autowired
    @Qualifier("gameStatusRedisTemplate")
    private RedisTemplate<String,GameStatus> redisTemplate;

	/**`
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
	
	protected void setIndexes(final GameStatus entity) {
		// Override to set custom indexes for lookups
	}

	protected void clearIndexes(final GameStatus Entity) {
		// Override to clear custom indexes for lookups
	}
	*/

	@Override
	@SuppressWarnings("unchecked")
	public GameStatus getByGame(final UUID gameId) {
		return (GameStatus) redisTemplate.opsForHash().get(OBJECT_KEY, gameId.toString());
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<GameStatus> getListByGames(final Collection<UUID> gameIds) {
		// need to convert the UUIDs to a collection of strings to get data back
		final Collection idsAsStringCollection = new ArrayList<String>();
		for (final UUID id : gameIds) {
			idsAsStringCollection.add(id.toString());
		}
		return (List<GameStatus>) (List<?>) redisTemplate.opsForHash().multiGet(OBJECT_KEY, idsAsStringCollection);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<GameStatus> list() {
		return (List<GameStatus>) (List<?>) redisTemplate.opsForHash().values(OBJECT_KEY);
	}

	@Override
	public void save(final GameStatus entity) {
		redisTemplate.opsForHash().put(OBJECT_KEY, entity.getGameId().toString(), entity);
	}


	@Override
	public void remove(final GameStatus entity) {
		redisTemplate.opsForHash().delete(OBJECT_KEY, entity.getGameId().toString());
	}
}
