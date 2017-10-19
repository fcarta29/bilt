package com.byteknowledge.bilt.dao;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.byteknowledge.bilt.model.GamePick;

public interface GamePickDao extends Dao<GamePick> {
	
	public List<GamePick> getByGames(final Collection<UUID> gameIds);
	
	public GamePick getByGameAndUser(final UUID gameId, final UUID userId);
	
}