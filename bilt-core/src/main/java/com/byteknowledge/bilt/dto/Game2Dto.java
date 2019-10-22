package com.byteknowledge.bilt.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Game2Dto implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3493644765826111446L;

    private UUID id;
    private GameTeamDto homeTeam;
    private GameTeamDto awayTeam;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
    private Date gameTime;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TeamDto getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(GameTeamDto homeTeam) {
        this.homeTeam = homeTeam;
    }

    public TeamDto getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(GameTeamDto awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Date getGameTime() {
        return gameTime;
    }

    public void setGameTime(Date gameTime) {
        this.gameTime = gameTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        Game2Dto other = (Game2Dto) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
