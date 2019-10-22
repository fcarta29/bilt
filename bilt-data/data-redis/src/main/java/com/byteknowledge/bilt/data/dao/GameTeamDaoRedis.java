package com.byteknowledge.bilt.data.dao;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.byteknowledge.bilt.dao.GameTeamDao;
import com.byteknowledge.bilt.model.GameTeam;

@Repository("gameTeamDao")
public class GameTeamDaoRedis extends AbstractDaoRedis<GameTeam> implements GameTeamDao {

    private static final Logger LOG = LogManager.getLogger(GameTeamDaoRedis.class);
    
    private static final String OBJECT_KEY = "GameTeam";
    private static final String GAME_TEAM_INDEX_KEY = "GameTeam:Game:{0}:Team{1}";

    @Bean(name = "gameTeamRedisTemplate")
    public RedisTemplate<String, GameTeam> redisTemplate() {
        return initRedisTemplate();
    }

    @Autowired
    @Qualifier("gameTeamRedisTemplate")
    private RedisTemplate<String, GameTeam> redisTemplate;

    @Autowired
    @Qualifier("redisIndexingTemplate")
    private RedisTemplate<String, String> redisIndexingTemplate;

    @Override
    public String getObjectKey() {
        return OBJECT_KEY;
    }

    @Override
    public RedisTemplate<String, GameTeam> getRedisTemplate() {
        return redisTemplate;
    }

    @Override
    protected void setIndexes(final GameTeam gameTeam) {
        redisIndexingTemplate.opsForSet().add(MessageFormat.format(GAME_TEAM_INDEX_KEY, gameTeam.getGameId(), gameTeam.getTeamId()), gameTeam.getId().toString());
    }

    @Override
    protected void clearIndexes(final GameTeam gameTeam) {
        if (gameTeam != null && gameTeam.getId() != null) {
            redisIndexingTemplate.opsForSet().remove(MessageFormat.format(GAME_TEAM_INDEX_KEY, gameTeam.getGameId(), gameTeam.getTeamId()), gameTeam.getId().toString());
        }
    }
    
    @Override
    public GameTeam findByGameAndTeam(final UUID gameId, final UUID teamId) {
        final Collection<UUID> gameTeamIds = convertStringsToUUIDs(redisIndexingTemplate.opsForSet().members(MessageFormat.format(GAME_TEAM_INDEX_KEY, gameId, teamId)));

        if (CollectionUtils.isEmpty(gameTeamIds)) {
            return null;
        }

        if (gameTeamIds.size() > 1) {
            throw new RuntimeException("Problem with game team data: more than one result found!");
        }

        // get the first off the iteration since there should only be one at this point
        return get(gameTeamIds.iterator().next());
    }
}
