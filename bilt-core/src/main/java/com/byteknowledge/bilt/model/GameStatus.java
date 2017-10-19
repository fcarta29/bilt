package com.byteknowledge.bilt.model;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GameStatus implements Serializable {

	private static final long serialVersionUID = -9019063760051148833L;

	public enum GameState {
		PRE, NOW, POST, POSTPONED, CANCELED;
	}

	public enum GamePeriod {
		FIRST((short) 1), SECOND((short) 2), THIRD((short) 3), FOURTH((short) 4);

		private final short period;

		private GamePeriod(final short period) {
			this.period = period;
		}

		public short getPeriod() {
			return this.period;
		}
	}

	private UUID gameId;
	private GameState state;
	private GamePeriod period;
	private Long clock;
	private Long lastModifiedTimestamp;

	public UUID getGameId() {
		return gameId;
	}

	public void setGameId(UUID gameId) {
		this.gameId = gameId;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public GamePeriod getPeriod() {
		return period;
	}

	public void setPeriod(GamePeriod period) {
		this.period = period;
	}

	public Long getClock() {
		return clock;
	}

	public void setClock(Long clock) {
		this.clock = clock;
	}
	
	public Long getLastModifiedTimestamp() {
		return lastModifiedTimestamp;
	}

	public void setLastModifiedTimestamp(Long lastModifiedTimestamp) {
		this.lastModifiedTimestamp = lastModifiedTimestamp;
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
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof GameStatus)) {
			return false;
		}
		GameStatus other = (GameStatus) obj;
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
        return ToStringBuilder.reflectionToString(this);
    }	

}
