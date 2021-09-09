package com.example.ProjetFInal.modeles;


import com.example.ProjetFInal.enumeration.Forme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document("medicament")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medicament {
    @Id
    private String id;
    private String code_cip;
    private  String nom_medoc;
    private Forme forme;
    private  String dosage_medoc;
    private  String notice_medoc;
    private  String conditionnement_medoc;
    private  String marque_medoc;
    private  String id_pharmacie;
    private  String added_by;
    private  int last_price;
    private List<String> categories;
    private List<String> tag;
    private Date created_at;


}
