package com.example.ProjetFInal.modeles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document( "utilisateur")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {

    @Id
    private String id ;
    private String username;
    private String firstname;
    private String password;
    private String email ;
    private Integer userphone ;
    private Integer etatuser ;
    private Boolean satutuser ;
    private Adresse adresse;
    private String id_pharma;

}
