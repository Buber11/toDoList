package com.example.toDoList.DTO.UserDTO;

import java.util.Objects;

public record SignUPAnswerDto(
        String name,
        String surname,
        String email
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignUPAnswerDto that = (SignUPAnswerDto) o;
        return Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, email);
    }
}
