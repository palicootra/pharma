package com.example.ProjetFInal.modeles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document("pharmacies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pharmacie {

    @Id
    private String id;
    private String nom_phar;
    private String email_phar;
    private String tel_phar;
    private String statu_phar;
    private Adresse adresse;
    private Date created_at;


}
