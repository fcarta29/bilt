package com.byteknowledge.bilt.data.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.byteknowledge.bilt.dao.Dao;
import com.byteknowledge.bilt.dao.GameDao;
import com.byteknowledge.bilt.dao.GamePickDao;
import com.byteknowledge.bilt.dao.GameWeekDao;
import com.byteknowledge.bilt.model.Game;
import com.byteknowledge.bilt.model.GamePick;
import com.byteknowledge.bilt.model.GameWeek;

@RestController
@RequestMapping("/games/picks")
public class GamePickRestController extends AbstractRestController<GamePick> {

	@Autowired
	private GameDao gameDao;
	
	@Autowired
	private GamePickDao gamePickDao;
	
	@Autowired 
	private GameWeekDao gameWeekDao;

	@Override
	protected Dao<GamePick> getDao() {
		return gamePickDao;
	}

	@Override
	protected void merge(GamePick persitedEntity, GamePick newEntity) {
		
	}	

	// get gamepicks by week	
	@RequestMapping(value = "/week/{weekId}", method = RequestMethod.GET, produces = "application/json")
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
	}
	
	/*
	// get gamepicks by week and user
	@RequestMapping(value = "/week/{weekId}/user/{userId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<GamePick> getGamePicksByWeekAndUser(@PathVariable("weekId") final UUID weekId, 
			@PathVariable("userId") final UUID userId) {
		// get all the games for that week
		final List<Game> games = gameDao.getByWeek(weekId);
		final List<GamePick> gamePicks = new ArrayList<GamePick>();
		// filter out all picks except those made by given user
		for (final Game game : games) {
			final GamePick gamePick = gamePickDao.getByGameAndUser(game.getId(), userId);
			if (gamePick != null) {
				gamePicks.add(gamePick);
			}
		}
		return gamePicks;
	}*/
}
