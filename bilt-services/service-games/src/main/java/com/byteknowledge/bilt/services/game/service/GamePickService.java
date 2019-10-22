package com.byteknowledge.bilt.services.game.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byteknowledge.bilt.dao.Dao;
import com.byteknowledge.bilt.dao.GamePickDao;
import com.byteknowledge.bilt.dto.GameDto;
import com.byteknowledge.bilt.dto.GamePickDto;
import com.byteknowledge.bilt.model.GamePick;
import com.byteknowledge.bilt.service.AbstractService;

@Service
public class GamePickService extends AbstractService<GamePickDto, GamePick, GamePickDao> {

    private static final Logger LOG = LogManager.getLogger(GamePickService.class);

    @Autowired
    private GameService gameService;

    @Autowired
    private GamePickDao gamePickDao;

    public Map<GameDto, Set<GamePickDto>> getGamePicksForWeek(final UUID weekId) {
        LOG.debug("Before weeks calls");
        final Collection<UUID> gameIdsForWeek = dtosToUUIDs(gameService.getGamesForWeek(weekId));
        LOG.debug("After weeks calls");

        LOG.debug("Before picks calls");
        return getGamePicksForGames(gameIdsForWeek);
    }

    public Map<GameDto, Set<GamePickDto>> getGamePicksForGames(final Collection<UUID> gameIds) {
        final Map<GameDto, Set<GamePickDto>> gamePickDtoMap = new HashMap<GameDto, Set<GamePickDto>>();

        LOG.debug("In picks calls " + gameIds.size());

        final Map<UUID, Set<GamePick>> gamePicksMap = gamePickDao.findByGames(gameIds);
        for (Map.Entry<UUID, Set<GamePick>> gamePicks : gamePicksMap.entrySet()) {
            final GameDto gameDto = gameService.getGame(gamePicks.getKey());
            final Set<GamePickDto> gamePicsDtos = new HashSet<GamePickDto>(entitiesToDtos(gamePicks.getValue()));
            gamePickDtoMap.put(gameDto, gamePicsDtos);
            LOG.debug("Added " + gameDto + " with " + gamePicsDtos.size() + " picks,  map size:" + gamePickDtoMap.keySet().size());
        }
        LOG.debug("Found " + gamePickDtoMap.keySet().size() + " games for " + gameIds.size() + " ids");

        return gamePickDtoMap;
    }

    public Set<GamePickDto> getGamePicksForWeekAndUser(final UUID weekId, final UUID userId) {
        final Collection<UUID> gameIdsForWeek = dtosToUUIDs(gameService.getGamesForWeek(weekId));

        return getGamePicksForGamesAndUser(gameIdsForWeek, userId);
    }

    public Set<GamePickDto> getGamePicksForGamesAndUser(final Collection<UUID> gameIds, final UUID userId) {
        final Set<GamePick> gamePicks = gamePickDao.findByGamesAndUser(gameIds, userId);

        return new HashSet<GamePickDto>(entitiesToDtos(gamePicks));
    }

    public Collection<GamePickDto> getGamePicks(final UUID gameId) {
        return entitiesToDtos(gamePickDao.findByGame(gameId));
    }

    @Override
    public GamePickDto entityToDto(GamePick gamePick) {
        final GamePickDto gamePickDto = new GamePickDto.GamePickBuilder(gamePick.getGameId().toString(), gamePick.getUserId().toString()).setId(gamePick.getId().toString())
                .setResult(Short.toString(gamePick.getResult())).setTeamId(gamePick.getTeamId().toString()).build();
        return gamePickDto;
    }

    @Override
    public GamePick dtoToEntity(GamePickDto gameDto) {
        throw new UnsupportedOperationException("NEED TO IMPLEMENT!");
    }

    @Override
    protected Dao<GamePick> getDao() {
        return gamePickDao;
    }
}
