package com.byteknowledge.bilt.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.byteknowledge.bilt.dao.Dao;
import com.byteknowledge.bilt.dao.GameDao;
import com.byteknowledge.bilt.model.Game;

@RestController
@RequestMapping("/games")
public class GameRestController extends AbstractRestController<Game> {

	@Autowired
	private GameDao gameDao;

	@Override
	protected Dao<Game> getDao() {
		return gameDao;
	}

	@Override
	protected void merge(final Game persistedGame, final Game game) {
    	persistedGame.setAwayTeamId(game.getAwayTeamId());
    	persistedGame.setHomeTeamId(game.getHomeTeamId());
    	persistedGame.setScoreMultplier(game.getScoreMultplier());
    	persistedGame.setStartTimestamp(game.getStartTimestamp());
	}

	// games by week
	
	// games by team
}
