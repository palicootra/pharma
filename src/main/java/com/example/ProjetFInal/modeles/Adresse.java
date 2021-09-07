package com.example.ProjetFInal.modeles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document("adresse")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adresse {

    @Id
    private String id_adresse;
    private String pays;
    private String ville;
    private String region;
    private String departement;
    private String longitude;
    private String latitude;
    private String code_postal;

}
