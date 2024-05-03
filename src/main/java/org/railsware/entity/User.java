package org.railsware.entity;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name")
    private final String name;
    @SerializedName("email")
    private final String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private String name;

        private String email;

        public Builder setName(final String name) {
            this.name = name;
            return this;
        }

        public Builder setEmail(final String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this.name, this.email);
        }
    }
}