package com.example.ProjetFInal.modeles;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document( "Utilisateur")
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

    public Utilisateur(){ super(); }
    public Utilisateur(String username, String firstname, String password, String email, Integer userphone, Integer etatuser, Boolean satutuser) {
        this.username = username;
        this.firstname = firstname;
        this.password = password;
        this.email = email;
        this.userphone = userphone;
        this.etatuser = etatuser;
        this.satutuser = satutuser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserphone() {
        return userphone;
    }

    public void setUserphone(Integer userphone) {
        this.userphone = userphone;
    }

    public Integer getEtatuser() {
        return etatuser;
    }

    public void setEtatuser(Integer etatuser) {
        this.etatuser = etatuser;
    }

    public Boolean getSatutuser() {
        return satutuser;
    }

    public void setSatutuser(Boolean satutuser) {
        this.satutuser = satutuser;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", userphone=" + userphone +
                ", etatuser=" + etatuser +
                ", satutuser=" + satutuser +
                '}';
    }
}
