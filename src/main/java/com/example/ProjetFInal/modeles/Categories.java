package com.example.ProjetFInal.modeles;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collation = "Categories")
public class Categories {
    @Id
    public String id;

    @Field(value = "Nom_cat")
    public String nom_cat;


    public Categories( String nom_cat) {

        this.nom_cat = nom_cat;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom_cat() {
        return nom_cat;
    }

    public void setNom_cat(String nom_cat) {
        this.nom_cat = nom_cat;
    }
}
