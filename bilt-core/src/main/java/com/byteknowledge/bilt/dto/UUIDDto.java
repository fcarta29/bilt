package com.byteknowledge.bilt.dto;

public abstract class UUIDDto extends AbstractDto {

	private static final long serialVersionUID = -6277074475465580961L;
	
	protected final String id;

    protected UUIDDto(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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
		if (!(obj instanceof UUIDDto)) {
			return false;
		}
		UUIDDto other = (UUIDDto) obj;
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
		return "UUIDDto [id=" + id + "]";
	}

}
