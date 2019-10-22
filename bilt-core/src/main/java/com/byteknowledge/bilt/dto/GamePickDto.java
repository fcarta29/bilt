package com.byteknowledge.bilt.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GamePickDto extends UUIDDto implements Serializable {

	private static final long serialVersionUID = -1990920979984819901L;

	private final String gameId;
	private final String userId;
	private final String teamId;
	private final String result;

    private GamePickDto(final GamePickBuilder builder) {
        super(builder.id);
        this.gameId = builder.gameId;
        this.userId = builder.userId;
        this.teamId = builder.teamId;
        this.result = builder.result;
    }
	
    public static final class GamePickBuilder {
    	private final String gameId;
    	private final String userId;
    	private String id;
    	private String teamId;
    	private String result;

        public GamePickBuilder(final String gameId, final String userId) {
        	this.gameId = gameId;
        	this.userId = userId;
        }

        public GamePickBuilder setId(final String id) {
            this.id = id;
            return this;
        }

        public GamePickBuilder setTeamId(final String teamId) {
            this.teamId = teamId;
            return this;
        }        
        
        public GamePickBuilder setResult(final String result) {
            this.result = result;
            return this;
        }
        
        public GamePickDto build() {
            return new GamePickDto(this);
        }
    }
	
	public String getGameId() {
		return gameId;
	}

	public String getUserId() {
		return userId;
	}

	public String getTeamId() {
		return teamId;
	}

	public String getResult() {
		return result;
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
		if (!(obj instanceof GamePickDto)) {
			return false;
		}
		GamePickDto other = (GamePickDto) obj;
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
