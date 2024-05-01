package com.example.toDoList.Models.Token;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long tokenId;
    @Column(name = "user_id")
    private Long userId;
    private String token;
    @Column(name = "token_expiration_date")
    private Date tokenExpirationDate;

    public static Builder builder() {
        return new Token().new Builder();
    }


    public class Builder {
        private Builder() {}

        public Builder userId(Long userId) {
            Token.this.userId = userId;
            return this;
        }

        public Builder token(String token) {
            Token.this.token = token;
            return this;
        }

        public Builder tokenExpirationDate(Date tokenExpirationDate) {
            Token.this.tokenExpirationDate = tokenExpirationDate;
            return this;
        }

        public Token build() {
            return Token.this;
        }
    }

}
