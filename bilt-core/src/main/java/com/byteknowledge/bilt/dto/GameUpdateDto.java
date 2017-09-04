package com.byteknowledge.bilt.dto;

import java.io.Serializable;

public class GameUpdateDto implements Serializable {

    private String gameId;

    public String getGameId() {
    	return gameId;
    }

    public void setGameId(String gameId) {
    	this.gameId = gameId;
    }

    @Override
    public int hashCode() {
    	final int prime = 31;
    	int result = 1;
    	result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
    	return result;
    }

    @Override
    public boolean equals(Object obj) {
    	if (this == obj) {
    		return true;
    	}
    	if (obj == null) {
    		return false;
    	}
    	if (!(obj instanceof GameUpdateDto)) {
    		return false;
    	}
    	GameUpdateDto other = (GameUpdateDto) obj;
    	if (gameId == null) {
    		if (other.gameId != null) {
    			return false;
    		}
    	} else if (!gameId.equals(other.gameId)) {
    		return false;
    	}
    	return true;
    }

    @Override
    public String toString() {
    	return "GameUpdateDto [gameId=" + gameId + "]";
    }
}
