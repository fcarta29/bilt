package com.byteknowledge.bilt.model;


import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class Game extends AbstractUUIDEntity {

	private static final long serialVersionUID = 6749014487716155255L;

	private UUID homeTeamId;
	private UUID awayTeamId;
	private Short scoreMultplier = Short.valueOf((short) 1);
	private Long startTimestamp;

	public UUID getHomeTeamId() {
		return homeTeamId;
	}

	public void setHomeTeamId(UUID homeTeamId) {
		this.homeTeamId = homeTeamId;
	}

	public UUID getAwayTeamId() {
		return awayTeamId;
	}

	public void setAwayTeamId(UUID awayTeamId) {
		this.awayTeamId = awayTeamId;
	}

	public Short getScoreMultplier() {
		return scoreMultplier;
	}

	public void setScoreMultplier(Short scoreMultplier) {
		this.scoreMultplier = scoreMultplier;
	}

	public Long getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(Long startTimestamp) {
		this.startTimestamp = startTimestamp;
	}	
	
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getHomeTeamId() == null) ? 0 : getHomeTeamId().hashCode());
        result = prime * result + ((getAwayTeamId() == null) ? 0 : getAwayTeamId().hashCode());
        result = prime * result + ((getStartTimestamp() == null) ? 0 : getStartTimestamp().hashCode());
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
		if (!(obj instanceof Game)) {
			return false;
		}
		Game other = (Game) obj;
		if (homeTeamId == null) {
			if (other.homeTeamId != null) {
				return false;
			}
		} else if (!homeTeamId.equals(other.homeTeamId)) {
			return false;
		}
		if (awayTeamId == null) {
			if (other.awayTeamId != null) {
				return false;
			}
		} else if (!awayTeamId.equals(other.awayTeamId)) {
			return false;
		}
		if (startTimestamp == null) {
			if (other.startTimestamp != null) {
				return false;
			}
		} else if (!startTimestamp.equals(other.startTimestamp)) {
			return false;
		}
        
        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
