package com.byteknowledge.bilt.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.byteknowledge.bilt.dao.Dao;
import com.byteknowledge.bilt.dao.GameWeekDao;
import com.byteknowledge.bilt.model.GameWeek;

@RestController
@RequestMapping("/weeks")
public class GameWeekRestController extends AbstractRestController<GameWeek> {
	
	@Autowired
	private GameWeekDao gameWeekDao;

	@Override
	protected Dao<GameWeek> getDao() {
		return gameWeekDao;
	}

	@Override
	protected void merge(final GameWeek persistedGameWeek, final GameWeek gameWeek) {
    	persistedGameWeek.setName(gameWeek.getName());
    	persistedGameWeek.setStartTimestamp(gameWeek.getStartTimestamp());
    	persistedGameWeek.setEndTimestamp(gameWeek.getEndTimestamp());
	}

}
