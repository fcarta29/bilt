package com.byteknowledge.bilt.dao;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.byteknowledge.bilt.model.GamePick;

public interface GamePickDao extends Dao<GamePick> {
	
	public Set<GamePick> findByGame(final UUID gameId);
	
	public Map<UUID,Set<GamePick>> findByGames(final Collection<UUID> gameIds);
	
	public Set<GamePick> findByUser(final UUID userId);
	
	public GamePick findByGameAndUser(final UUID gameId, final UUID userId);
	
	public Set<GamePick> findByGamesAndUser(final Collection<UUID> gameIds, final UUID userId);
	
}