package com.byteknowledge.bilt.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GameUpdateDto implements Serializable {

	private static final long serialVersionUID = -1990920979984819901L;

	private String gameId;
	private String homeTeamId;
	private String homeTeamScore;
	private String homeTeamSpread;
	private String awayTeamId;
	private String awayTeamScore;
	private String awayTeamSpread;
	private String startTime;
	private String period;
	private String clock;
	private String status;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getHomeTeamId() {
		return homeTeamId;
	}

	public void setHomeTeamId(String homeTeamId) {
		this.homeTeamId = homeTeamId;
	}

	public String getHomeTeamScore() {
		return homeTeamScore;
	}

	public void setHomeTeamScore(String homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}

	public String getHomeTeamSpread() {
		return homeTeamSpread;
	}

	public void setHomeTeamSpread(String homeTeamSpread) {
		this.homeTeamSpread = homeTeamSpread;
	}

	public String getAwayTeamId() {
		return awayTeamId;
	}

	public void setAwayTeamId(String awayTeamId) {
		this.awayTeamId = awayTeamId;
	}

	public String getAwayTeamScore() {
		return awayTeamScore;
	}

	public void setAwayTeamScore(String awayTeamScore) {
		this.awayTeamScore = awayTeamScore;
	}

	public String getAwayTeamSpread() {
		return awayTeamSpread;
	}

	public void setAwayTeamSpread(String awayTeamSpread) {
		this.awayTeamSpread = awayTeamSpread;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getClock() {
		return clock;
	}

	public void setClock(String clock) {
		this.clock = clock;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		return ToStringBuilder.reflectionToString(this);
	}
}
