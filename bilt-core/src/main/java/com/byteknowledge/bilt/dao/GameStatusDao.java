package com.byteknowledge.bilt.dao;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.byteknowledge.bilt.model.GameStatus;

public interface GameStatusDao {

	public GameStatus getByGame(final UUID gameId);

	public List<GameStatus> getListByGames(final Collection<UUID> gameIds);

    public List<GameStatus> list();

    public void save(final GameStatus entity);

    public void remove(final GameStatus entity);
}
