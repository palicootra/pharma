package com.example.ProjetFInal.modeles;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Date;
import java.util.List;

@Document( "utilisateur")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {

    @Id
    private String id ;
    @Indexed(unique=true)
    @NonNull
    private String username;
    private String firstname;
    @NonNull
    private String password;
    @Indexed(unique=true)
    @NonNull
    private String email ;
    private String userphone ;
    private Integer etatuser ;
    private Boolean satutuser ;
    private Boolean sex ;
    private Adresse adresse;
    private String id_pharma;
    private Date created_at;
    private List<String>  roles;


}
