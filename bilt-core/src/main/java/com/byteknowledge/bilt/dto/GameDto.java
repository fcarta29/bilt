package com.byteknowledge.bilt.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class GameDto extends UUIDDto implements Serializable {

    private static final long serialVersionUID = 6040581995653117204L;

    private final String gameName;

    private GameDto(final GameBuilder builder) {
        super(builder.id);
        this.gameName = builder.gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public static final class GameBuilder {
        private final String gameName;
        private String id;

        public GameBuilder(final String gameName) {
            this.gameName = gameName;
        }

        public GameBuilder setId(final String id) {
            this.id = id;
            return this;
        }

        public GameDto build() {
            return new GameDto(this);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((gameName == null) ? 0 : gameName.hashCode());
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
        if (!(obj instanceof GameDto)) {
            return false;
        }
        GameDto other = (GameDto) obj;
        if (gameName == null) {
            if (other.gameName != null) {
                return false;
            }
        } else if (!gameName.equals(other.gameName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
