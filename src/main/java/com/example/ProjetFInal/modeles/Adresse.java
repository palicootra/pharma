package com.example.ProjetFInal.modeles;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Adresse {

    @Id
    private String id_adresse;

    @Field
    private String pays;

    @Field
    private String ville;

    @Field
    private String region;

    @Field
    private String departement;

    @Field
    private String longitude;

    @Field
    private String latitude;

    @Field
    private String code_postal;

    public Adresse(String pays, String ville, String region, String departement, String longitude, String latitude, String code_postal) {
        this.pays = pays;
        this.ville = ville;
        this.region = region;
        this.departement = departement;
        this.longitude = longitude;
        this.latitude = latitude;
        this.code_postal = code_postal;
    }

    public String getId_adresse() {
        return id_adresse;
    }

    public void setId_adresse(String id_adresse) {
        this.id_adresse = id_adresse;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }
}
