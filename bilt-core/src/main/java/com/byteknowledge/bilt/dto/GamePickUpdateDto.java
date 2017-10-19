package com.byteknowledge.bilt.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GamePickUpdateDto implements Serializable {

	private static final long serialVersionUID = -1990920979984819901L;

	private String gameId;
	private String userId;
	private String teamId;
	private String lastModifiedTimestamp;
	private String result;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getLastModifiedTimestamp() {
		return lastModifiedTimestamp;
	}

	public void setLastModifiedTimestamp(String lastModifiedTimestamp) {
		this.lastModifiedTimestamp = lastModifiedTimestamp;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());		
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
		if (!(obj instanceof GamePickUpdateDto)) {
			return false;
		}
		GamePickUpdateDto other = (GamePickUpdateDto) obj;
		if (gameId == null) {
			if (other.gameId != null) {
				return false;
			}
		} else if (!gameId.equals(other.gameId)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}		

		return true;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
