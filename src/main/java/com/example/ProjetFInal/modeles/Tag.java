package com.example.ProjetFInal.modeles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @Id
    private String id_tag;

    private  String libele;


}
