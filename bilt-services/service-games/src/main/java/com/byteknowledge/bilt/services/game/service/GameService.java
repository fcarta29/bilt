package com.byteknowledge.bilt.services.game.service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byteknowledge.bilt.dao.Dao;
import com.byteknowledge.bilt.dao.GameDao;
import com.byteknowledge.bilt.dao.GameWeekDao;
import com.byteknowledge.bilt.dto.GameDto;
import com.byteknowledge.bilt.model.Game;
import com.byteknowledge.bilt.model.GameWeek;
import com.byteknowledge.bilt.service.AbstractService;

@Service
public class GameService extends AbstractService<GameDto, Game, GameDao> {

    private static final Logger LOG = LogManager.getLogger(GameService.class);

    @Autowired
    private GameDao gameDao;

    @Autowired
    private GameWeekDao gameWeekDao;


    public List<GameDto> getGamesList() {
        return (List<GameDto>) getAll();
    }

    public Collection<GameDto> getGamesForWeek(final UUID weekId) {
        final GameWeek gameWeek = gameWeekDao.get(weekId);
        if (gameWeek == null) {
            // throw new IllegalArgumentException("Week " + weekId + " cannot be found!");
            return CollectionUtils.emptyCollection();
        }

        final Collection<GameDto> games = getGamesByDateRange(gameWeek.getStartTimestamp(), gameWeek.getEndTimestamp());
        LOG.debug("Found " + games.size() + " games for week: " + gameWeek.getName());

        return games;
    }

    private Collection<GameDto> getGamesByDateRange(final long startTimestamp, final long endTimestamp) {
        return entitiesToDtos(gameDao.findByDateRange(startTimestamp, endTimestamp));
    }

    public GameDto getGame(final UUID gameId) {
        return get(gameId);
    }

    @Override
    public GameDto entityToDto(Game game) {
        final GameDto gameDto = new GameDto.GameBuilder(game.getId().toString())
                .setId(game.getId().toString())
                .build();
        return gameDto;
    }

    @Override
    public Game dtoToEntity(GameDto gameDto) {
        throw new UnsupportedOperationException("NEED TO IMPLEMENT!");
    }

    @Override
    protected Dao<Game> getDao() {
        return gameDao;
    }
    /*
     * @Override protected void merge(Game persistedGame, Game game) {
     * persistedGame.setAwayTeamId(game.getAwayTeamId());
     * persistedGame.setHomeTeamId(game.getHomeTeamId());
     * persistedGame.setScoreMultplier(game.getScoreMultplier());
     * persistedGame.setStartTimestamp(game.getStartTimestamp()); }
     */

}
