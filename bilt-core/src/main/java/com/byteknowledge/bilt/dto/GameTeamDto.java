package com.byteknowledge.bilt.dto;

import java.io.Serializable;

public class GameTeamDto extends TeamDto implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5604145691772874106L;

    private Float spread = Float.valueOf(0f);
    private Float score = Float.valueOf(0f);
    private Long lastUpdatedTime = Long.valueOf(0l);

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

}
