package com.byteknowledge.bilt.dao;

import java.util.UUID;

import com.byteknowledge.bilt.model.GameTeam;

public interface GameTeamDao extends Dao<GameTeam> {
    
    public GameTeam findByGameAndTeam(final UUID gameId, final UUID teamId);
}