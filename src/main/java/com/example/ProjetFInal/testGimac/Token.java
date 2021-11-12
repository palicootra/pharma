package com.example.ProjetFInal.testGimac;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    String access_token;
    String token_type;
    String refresh_token;
    long expires_in;
    String scope;
}
