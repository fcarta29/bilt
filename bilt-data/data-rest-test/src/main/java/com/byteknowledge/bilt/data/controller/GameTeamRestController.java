package com.byteknowledge.bilt.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.byteknowledge.bilt.dao.Dao;
import com.byteknowledge.bilt.dao.GameTeamDao;
import com.byteknowledge.bilt.model.GameTeam;

@RestController
@RequestMapping("/games/teams")
public class GameTeamRestController extends AbstractRestController<GameTeam> {

    @Autowired
    private GameTeamDao gameTeamDao;
    
    @Override
    protected Dao<GameTeam> getDao() {
        return gameTeamDao;
    }
/*
    // get gamepicks by week        
    @RequestMapping(value = "{weekId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Map<UUID, Set<GamePick>> getGamePicksByWeek(@PathVariable("weekId") final UUID weekId) {
            final GameWeek gameWeek = gameWeekDao.get(weekId);
            
            // need to get all the games for that week
            final Set<Game> games = gameDao.findByDateRange(gameWeek.getStartTimestamp(), gameWeek.getEndTimestamp());
                            
            final List<UUID> gameIds = new ArrayList<UUID>();
            for (final Game game : games) {
                    gameIds.add(game.getId());
            }
            // return all the picks for the given games
            return gamePickDao.findByGames(gameIds);
    }*/
    
    @Override
    protected void merge(final GameTeam persistedTeam, final GameTeam team) {
        persistedTeam.setGameId(team.getGameId());
        persistedTeam.setTeamId(team.getTeamId());
        persistedTeam.setScore(team.getScore());
        persistedTeam.setSpread(team.getSpread());
    }    
}