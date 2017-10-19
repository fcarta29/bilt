package com.byteknowledge.bilt.model;

import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GameTeam extends AbstractUUIDEntity {

	private static final long serialVersionUID = 6183005029466642746L;

	private UUID teamId;
	private UUID gameId;
	private Float spread = Float.valueOf(0f);
	private Float score = Float.valueOf(0f);
	private Long lastUpdatedTime = Long.valueOf(0l);

	public UUID getTeamId() {
		return teamId;
	}

	public void setTeamId(UUID teamId) {
		this.teamId = teamId;
	}

	public UUID getGameId() {
		return gameId;
	}

	public void setGameId(UUID gameId) {
		this.gameId = gameId;
	}

	public Float getSpread() {
		return spread;
	}

	public void setSpread(Float spread) {
		this.spread = spread;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

    public Long getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Long lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((teamId == null) ? 0 : teamId.hashCode());
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
		if (!(obj instanceof GameTeam)) {
			return false;
		}
		GameTeam other = (GameTeam) obj;
		if (teamId == null) {
			if (other.teamId != null) {
				return false;
			}
		} else if (!teamId.equals(other.teamId)) {
			return false;
		}
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
