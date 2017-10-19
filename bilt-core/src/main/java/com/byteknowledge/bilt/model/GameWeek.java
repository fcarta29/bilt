package com.byteknowledge.bilt.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GameWeek extends AbstractUUIDEntity {

	private static final long serialVersionUID = 8009680929833349902L;

	private String name;
	private Long startTimestamp;
	private Long endTimestamp;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(Long startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public Long getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(Long endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((getStartTimestamp() == null) ? 0 : getStartTimestamp().hashCode());
        result = prime * result + ((getEndTimestamp() == null) ? 0 : getEndTimestamp().hashCode());
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
		if (!(obj instanceof GameWeek)) {
			return false;
		}
		GameWeek other = (GameWeek) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (startTimestamp == null) {
			if (other.startTimestamp != null) {
				return false;
			}
		} else if (!startTimestamp.equals(other.startTimestamp)) {
			return false;
		}
		if (endTimestamp == null) {
			if (other.endTimestamp != null) {
				return false;
			}
		} else if (!endTimestamp.equals(other.endTimestamp)) {
			return false;
		}		
		
        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
