package com.example.ProjetFInal.modeles;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collation = "Pharmacies")
public class Pharmacie {

    @Id
    private String id;

    @Field
    private String nom_phar;

    @Field
    private String email_phar;

    @Field
    private Integer tel_phar;

    @Field
    private String statu_phar;

    public Pharmacie(String nom_phar, String email_phar, Integer tel_phar, String statu_phar) {
        this.nom_phar = nom_phar;
        this.email_phar = email_phar;
        this.tel_phar = tel_phar;
        this.statu_phar = statu_phar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom_phar() {
        return nom_phar;
    }

    public void setNom_phar(String nom_phar) {
        this.nom_phar = nom_phar;
    }

    public String getEmail_phar() {
        return email_phar;
    }

    public void setEmail_phar(String email_phar) {
        this.email_phar = email_phar;
    }

    public Integer getTel_phar() {
        return tel_phar;
    }

    public void setTel_phar(Integer tel_phar) {
        this.tel_phar = tel_phar;
    }

    public String getStatu_phar() {
        return statu_phar;
    }

    public void setStatu_phar(String statu_phar) {
        this.statu_phar = statu_phar;
    }
}
