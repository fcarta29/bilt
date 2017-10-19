package com.byteknowledge.bilt.dao;

import java.util.List;
import java.util.UUID;

import com.byteknowledge.bilt.model.Game;

public interface GameDao extends Dao<Game> {

	public List<Game> getByWeek(final UUID weekId);
}
