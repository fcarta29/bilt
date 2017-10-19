package com.byteknowledge.bilt.data.dao;

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

	/**
	 * NOTE[fcarta] - RedisTemplate is only able to contain one instance of
	 * XxxValueSerializer therefore we have to init the RedisTemplate here and
	 * add the following in each extending class
	 *
	 * @Bean(name="tileRedisTemplate") public RedisTemplate
	 *                                 <String,Tile> redisTemplate() { return
	 *                                 initRedisTemplate(); }
	 *
	 * @Autowired @Qualifier("tileRedisTemplate") private RedisTemplate
	 *            <String,Tile> redisTemplate = new RedisTemplate
	 *            <String,Tile>();
	 *
	 * @Override public RedisTemplate<String,Tile> getRedisTemplate() { return
	 *           redisTemplate; }
	 *
	 *
	 * @return
	 *
	protected RedisTemplate<String, E> initRedisTemplate() {
		final RedisTemplate<String, E> redisTemplate = new RedisTemplate<String, E>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
		// NOTE[fcarta] - TX seem to perform really poorly disabling for now
		// redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<E>(typeOfEntity));
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
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
	}*/

	@Override
	@SuppressWarnings("unchecked")
	public GameStatus get(final UUID gameId) {
		//return (GameStatus) redisTemplate.opsForHash().get(OBJECT_KEY, gameId.toString());
		throw new UnsupportedOperationException("");
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<GameStatus> getList(final Collection<UUID> gameIds) {
		/*
		// need to convert the UUIDs to a collection of strings to get data back
		final Collection idsAsStringCollection = new ArrayList<String>();
		for (final UUID id : gameIds) {
			idsAsStringCollection.add(id.toString());
		}
		return (List<GameStatus>) (List<?>) redisTemplate.opsForHash().multiGet(OBJECT_KEY, idsAsStringCollection);
	*/
		throw new UnsupportedOperationException("");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<GameStatus> list() {
		//return (List<GameStatus>) (List<?>) redisTemplate.opsForHash().values(OBJECT_KEY);
		throw new UnsupportedOperationException("");
	}

	@Override
	public void save(final GameStatus entity) {
		throw new UnsupportedOperationException("");
		/*
		boolean isUpdate = Boolean.TRUE;
		if (entity.getId() == null) {
			entity.setId(UUID.randomUUID());
			// this is a new record
			isUpdate = Boolean.FALSE;
		}
		// TODO[fcarta] - should make this all transactional
		if (isUpdate) {
			// clear out old indexes and update
			final GameStatus oldEntity = (GameStatus) redisTemplate.opsForHash().get(OBJECT_KEY, entity.getId().toString());
			clearIndexes(oldEntity);
		}
		redisTemplate.opsForHash().put(OBJECT_KEY, entity.getId().toString(), entity);
		setIndexes(entity);*/
	}

	protected void setIndexes(final GameStatus entity) {
		// Override to set custom indexes for lookups
	}

	protected void clearIndexes(final GameStatus Entity) {
		// Override to clear custom indexes for lookups
	}

	@Override
	public void remove(final GameStatus entity) {
		throw new UnsupportedOperationException("");
		//clearIndexes(entity);
		//redisTemplate.opsForHash().delete(OBJECT_KEY, entity.getGameId().toString());
	}
}
