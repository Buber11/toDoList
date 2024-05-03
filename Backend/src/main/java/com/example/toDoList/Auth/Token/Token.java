package com.example.toDoList.Auth.Token;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Builder
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

}
