package com.byteknowledge.bilt.data.dao;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.byteknowledge.bilt.dao.GamePickDao;
import com.byteknowledge.bilt.model.GamePick;

@Repository("gamePickDao")
public class GamePickDaoRedis extends AbstractDaoRedis<GamePick> implements GamePickDao {

    private static final Logger LOG = LogManager.getLogger(GamePickDaoRedis.class);

    private static final String OBJECT_KEY = "GamePick";
    private static final String GAME_INDEX_KEY = "GamePick:Game:{0}";
    private static final String USER_INDEX_KEY = "GamePick:User:{0}";
    private static final String GAME_USER_INDEX_KEY = "GamePick:Game:{0}:User{1}";

    @Bean(name = "gamePickRedisTemplate")
    public RedisTemplate<String, GamePick> redisTemplate() {
        return initRedisTemplate();
    }

    @Autowired
    @Qualifier("gamePickRedisTemplate")
    private RedisTemplate<String, GamePick> redisTemplate;

    @Autowired
    @Qualifier("redisIndexingTemplate")
    private RedisTemplate<String, String> redisIndexingTemplate;

    @Override
    public String getObjectKey() {
        return OBJECT_KEY;
    }

    @Override
    public RedisTemplate<String, GamePick> getRedisTemplate() {
        return redisTemplate;
    }

    @Override
    protected void setIndexes(final GamePick gamePick) {
        redisIndexingTemplate.opsForSet().add(MessageFormat.format(GAME_INDEX_KEY, gamePick.getGameId()), gamePick.getId().toString());
        redisIndexingTemplate.opsForSet().add(MessageFormat.format(USER_INDEX_KEY, gamePick.getUserId()), gamePick.getId().toString());
        redisIndexingTemplate.opsForSet().add(MessageFormat.format(GAME_USER_INDEX_KEY, gamePick.getGameId(), gamePick.getUserId()), gamePick.getId().toString());
    }

    @Override
    protected void clearIndexes(final GamePick gamePick) {
        if (gamePick != null && gamePick.getId() != null) {
            redisIndexingTemplate.opsForSet().remove(MessageFormat.format(GAME_INDEX_KEY, gamePick.getGameId()), gamePick.getId().toString());
            redisIndexingTemplate.opsForSet().remove(MessageFormat.format(USER_INDEX_KEY, gamePick.getUserId()), gamePick.getId().toString());
            redisIndexingTemplate.opsForSet().remove(MessageFormat.format(GAME_USER_INDEX_KEY, gamePick.getGameId(), gamePick.getUserId()), gamePick.getId().toString());
        }
    }

    @Override
    public Set<GamePick> findByGame(final UUID gameId) {
        final Collection<UUID> gamePickIds = convertStringsToUUIDs(redisIndexingTemplate.opsForSet().members(MessageFormat.format(GAME_INDEX_KEY, gameId)));

        LOG.debug("Found " + gamePickIds.size() + " picks for gameId: " + gameId);

        /*
         * final Set<String> gamePickIds = redisIndexingTemplate.opsForSet()
         * .members(MessageFormat.format(GAME_INDEX_KEY, gameId));
         */

        if (CollectionUtils.isEmpty(gamePickIds)) {
            return Collections.emptySet();
        }
        /*
         * @SuppressWarnings({ "unchecked", "rawtypes" }) final List<GamePick> gamePicks
         * = (List<GamePick>) (List<?>) getRedisTemplate().opsForHash()
         * .multiGet(getObjectKey(), (Collection) gamePickIds);
         */

        return new HashSet<GamePick>(getList(gamePickIds));
    }

    @Override
    public Map<UUID, Set<GamePick>> findByGames(final Collection<UUID> gameIds) {
        final Map<UUID, Set<GamePick>> gamePicks = new HashMap<UUID, Set<GamePick>>();

        LOG.debug("Searching " + gameIds.size() + " for game picks.");

        for (final UUID gameId : gameIds) {
            LOG.debug("Searching for game picks gameId: " + gameId.toString());
            gamePicks.put(gameId, findByGame(gameId));
        }

        LOG.debug("Games found by games: " + gamePicks.size());
        return gamePicks;
    }

    @Override
    public Set<GamePick> findByUser(UUID userId) {
        final Collection<UUID> gamePickIds = convertStringsToUUIDs(redisIndexingTemplate.opsForSet().members(MessageFormat.format(USER_INDEX_KEY, userId)));

        if (CollectionUtils.isEmpty(gamePickIds)) {
            return Collections.emptySet();
        }

        /*
         * @SuppressWarnings({ "unchecked", "rawtypes" }) final List<GamePick> gamePicks
         * = (List<GamePick>) (List<?>) getRedisTemplate().opsForHash()
         * .multiGet(getObjectKey(), (Collection) gamePickIds);
         */

        return new HashSet<GamePick>(getList(gamePickIds));
    }

    @Override
    public GamePick findByGameAndUser(final UUID gameId, final UUID userId) {
        @SuppressWarnings({ "unchecked" })
        final Collection<UUID> gamePickIds = convertStringsToUUIDs(redisIndexingTemplate.opsForSet().members(MessageFormat.format(GAME_USER_INDEX_KEY, gameId, userId)));

        if (CollectionUtils.isEmpty(gamePickIds)) {
            return null;
        }

        if (gamePickIds.size() > 1) {
            throw new RuntimeException("Problem with game pick data: more than one result found!");
        }

        // get the first off the iteration since there should only be one at this point
        return get(gamePickIds.iterator().next());
    }

    @Override
    public Set<GamePick> findByGamesAndUser(Collection<UUID> gameIds, UUID userId) {
        final Set<GamePick> gamePicks = new HashSet<GamePick>();

        for (final UUID gameId : gameIds) {
            final GamePick gamePick = findByGameAndUser(gameId, userId);
            if (gamePick != null) {
                gamePicks.add(gamePick);
            }
        }

        if (CollectionUtils.isEmpty(gamePicks)) {
            return Collections.emptySet();
        }

        return gamePicks;
    }
}
