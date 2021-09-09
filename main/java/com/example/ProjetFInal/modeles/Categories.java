package com.example.ProjetFInal.modeles;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categories {
    @Id
    public String id;

    public String nom_cat;
    private String description;


}
