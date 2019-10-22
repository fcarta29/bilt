package com.byteknowledge.bilt.data.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
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
    private static final String START_INDEX_KEY = "Game:Start";

    @Bean(name = "gameRedisTemplate")
    public RedisTemplate<String, Game> redisTemplate() {
        return initRedisTemplate();
    }

    @Autowired
    @Qualifier("gameRedisTemplate")
    private RedisTemplate<String, Game> redisTemplate;

    @Autowired
    @Qualifier("redisIndexingTemplate")
    private RedisTemplate<String, String> redisIndexingTemplate;

    @Override
    public String getObjectKey() {
        return OBJECT_KEY;
    }

    @Override
    public RedisTemplate<String, Game> getRedisTemplate() {
        return redisTemplate;
    }

    @Override
    protected void setIndexes(final Game game) {
        redisIndexingTemplate.opsForZSet().add(START_INDEX_KEY, game.getId().toString(), game.getStartTimestamp());
    }

    @Override
    protected void clearIndexes(final Game game) {
        if (game != null && game.getId() != null) {
            redisIndexingTemplate.opsForZSet().remove(START_INDEX_KEY, game.getId().toString());
        }
    }

    @Override
    public Set<Game> findByDateRange(final Long startTimestamp, final Long endTimestamp) {
        final Collection<String> gameIdsInRange = new LinkedHashSet<String>();

        gameIdsInRange.addAll(redisIndexingTemplate.opsForZSet().rangeByScore(START_INDEX_KEY, startTimestamp, endTimestamp));

        if (CollectionUtils.isEmpty(gameIdsInRange)) {
            return Collections.emptySet();
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
        final List<Game> games = (List<Game>) (List<?>) getRedisTemplate().opsForHash().multiGet(getObjectKey(), (Collection) gameIdsInRange);

        return new HashSet<Game>(games);
    }

}
