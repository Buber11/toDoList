package com.example.toDoList.payload.response;

public record UserInfoResponse(
        String name,
        String surname,
        String email
) {

    public static class Builder {
        private String name;
        private String surname;
        private String email;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public UserInfoResponse build() {
            return new UserInfoResponse(name, surname, email);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
