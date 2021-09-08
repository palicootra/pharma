package com.example.ProjetFInal.modeles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;


@Document("sorties")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sortie {

    @Id
    private String id;
    private Date date_sort;
    private Integer qte_sort;
    private String id_medoc;
    private String id_pharmacie;
}
