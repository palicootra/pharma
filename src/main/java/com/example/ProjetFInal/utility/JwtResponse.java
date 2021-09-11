package com.example.ProjetFInal.utility;


import com.example.ProjetFInal.modeles.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String jwtToken;
    private Utilisateur utilisateur;
}
