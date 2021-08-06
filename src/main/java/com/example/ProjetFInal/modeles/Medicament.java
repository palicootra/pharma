package com.example.ProjetFInal.modeles;


import com.example.ProjetFInal.enumeration.Forme;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collation = "Medicament")
public class Medicament {
    @Id
    private String id;

    @Field(value = "code_cip")
    private String code_cip;

    @Field(value = "nom_medoc")
    private  String nom_medoc;

    @Field(value = "forme_medoc")
    private Forme forme;

    @Field(value = "dosage_medoc")
    private  String dosage_medoc;

    @Field(value = "notice_medoc")
    private  String notice_medoc;

    @Field("conditionnemeent_medoc")
    private  String conditionnement_medoc;

    @Field(value = "marque_medoc")
    private  String marque_medoc;

    public Medicament(String nom_medoc, Forme forme_medoc, String dosage_medoc, String notice_medoc, String conditionnement_medoc, String marque_medoc) {
        this.nom_medoc = nom_medoc;
        this.forme = forme_medoc;
        this.dosage_medoc = dosage_medoc;
        this.notice_medoc = notice_medoc;
        this.conditionnement_medoc = conditionnement_medoc;
        this.marque_medoc = marque_medoc;
    }

    public String getCode_cip() {
        return code_cip;
    }

    public void setCode_cip(String code_cip) {
        this.code_cip = code_cip;
    }

    public String getNom_medoc() {
        return nom_medoc;
    }

    public void setNom_medoc(String nom_medoc) {
        this.nom_medoc = nom_medoc;
    }

    public Forme getForme_medoc() {
        return forme;
    }

    public void setForme_medoc(Forme forme_medoc) {
        this.forme = forme_medoc;
    }

    public String getDosage_medoc() {
        return dosage_medoc;
    }

    public void setDosage_medoc(String dosage_medoc) {
        this.dosage_medoc = dosage_medoc;
    }

    public String getNotice_medoc() {
        return notice_medoc;
    }

    public void setNotice_medoc(String notice_medoc) {
        this.notice_medoc = notice_medoc;
    }

    public String getConditionnement_medoc() {
        return conditionnement_medoc;
    }

    public void setConditionnement_medoc(String conditionnement_medoc) {
        this.conditionnement_medoc = conditionnement_medoc;
    }

    public String getMarque_medoc() {
        return marque_medoc;
    }

    public void setMarque_medoc(String marque_medoc) {
        this.marque_medoc = marque_medoc;
    }
}
