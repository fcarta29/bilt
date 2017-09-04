package com.byteknowledge.bilt.dto;

import java.io.Serializable;


public class UserDto extends UUIDDto implements Serializable {

    private static final long serialVersionUID = 6040581995653117204L;

    private final String userName;
    private final String firstName;
    private final String lastName;

    private UserDto(final UserBuilder builder) {
        super(builder.id);
        this.userName = builder.userName;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static final class UserBuilder {
        private final String userName;
        private String id;
        private String firstName;
        private String lastName;

        public UserBuilder(final String userName) {
            this.userName = userName;
        }

        public UserBuilder setId(final String id) {
            this.id = id;
            return this;
        }

        public UserBuilder setFirstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
        if (!(obj instanceof UserDto)) {
            return false;
        }
        UserDto other = (UserDto) obj;
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        if (userName == null) {
            if (other.userName != null) {
                return false;
            }
        } else if (!userName.equals(other.userName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}
