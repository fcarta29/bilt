package com.byteknowledge.bilt.dao;

import java.util.Set;

import com.byteknowledge.bilt.model.Game;

public interface GameDao extends Dao<Game> {

	public Set<Game> findByDateRange(final Long startTimestamp, final Long endTimestamp);
}
